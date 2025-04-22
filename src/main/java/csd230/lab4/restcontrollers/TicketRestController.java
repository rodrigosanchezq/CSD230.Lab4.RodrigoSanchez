package csd230.lab4.restcontrollers;

import csd230.lab4.entities.Ticket;
import csd230.lab4.respositories.TicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/ticket")
public class TicketRestController {
    private final TicketRepository ticketRepository;

    public TicketRestController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Operation(summary = "Get a magazine by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket found"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> ResponseEntity.ok().body(ticket))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets found"),
            @ApiResponse(responseCode = "404", description = "No tickets found")
    })
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        if (tickets.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tickets);
        }
    }

    @Operation(summary = "Create a new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        if (ticket.getText() == null) {
            return ResponseEntity.badRequest().build();
        }
        Ticket createdTicket = ticketRepository.save(ticket);
        return ResponseEntity.status(201).body(createdTicket);
    }
    @Operation(summary = "Update a ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketRepository.findById(id)
                .map(existingTicket -> {
                    existingTicket.setText(ticket.getText());
                    existingTicket.setPrice(ticket.getPrice());
                    existingTicket.setQuantity(ticket.getQuantity());
                    existingTicket.setDescription(ticket.getDescription());
                    Ticket updatedTicket = ticketRepository.save(existingTicket);
                    return ResponseEntity.ok(updatedTicket);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket deleted"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticketRepository.delete(ticket);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}