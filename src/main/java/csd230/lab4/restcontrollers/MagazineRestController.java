package csd230.lab4.restcontrollers;


import csd230.lab4.entities.Magazine;
import csd230.lab4.respositories.MagazineRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/magazine")
public class MagazineRestController {
    private final MagazineRepository magazineRepository;

    public MagazineRestController(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    // Add methods to handle RESTful requests for magazines
    // For example, you can add methods to get all magazines, get a magazine by ID,
    // create a new magazine, update an existing magazine, and delete a magazine.
    // Example method to get all magazines


    @Operation(summary = "Get a magazine by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Magazine found"),
            @ApiResponse(responseCode = "404", description = "Magazine not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Magazine> getMagazineById(@PathVariable Long id) {
        return magazineRepository.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get all magazines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Magazines found"),
            @ApiResponse(responseCode = "404", description = "No magazines found")
    })
    @GetMapping
    public ResponseEntity<List<Magazine>> getAllMagazines() {
        List<Magazine> magazines = (List<Magazine>) magazineRepository.findAll();
        if (magazines.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(magazines);
        }
    }

    @Operation(summary = "Create a new magazine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Magazine created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Magazine> createMagazine(@RequestBody Magazine magazine) {
        if (magazine.getTitle() == null || magazine.getCurrIssue() == null) {
            return ResponseEntity.badRequest().build();
        }
        Magazine createdMagazine = magazineRepository.save(magazine);
        return ResponseEntity.status(201).body(createdMagazine);
    }
    @Operation(summary = "Update a magazine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Magazine updated"),
            @ApiResponse(responseCode = "404", description = "Magazine not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Magazine> updateMagazine(@PathVariable Long id, @RequestBody Magazine magazine) {
        return magazineRepository.findById(id)
                .map(existingMagazine -> {
                    existingMagazine.setTitle(magazine.getTitle());
                    existingMagazine.setPrice(magazine.getPrice());
                    existingMagazine.setCurrIssue(magazine.getCurrIssue());
                    existingMagazine.setQuantity(magazine.getQuantity());
                    existingMagazine.setDescription(magazine.getDescription());
                    Magazine updatedMagazine = magazineRepository.save(existingMagazine);
                    return ResponseEntity.ok(updatedMagazine);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a magazine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Magazine deleted"),
            @ApiResponse(responseCode = "404", description = "Magazine not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMagazine(@PathVariable Long id) {
        return magazineRepository.findById(id)
                .map(magazine -> {
                    magazineRepository.delete(magazine);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
