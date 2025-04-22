package csd230.lab4.restcontrollers;

public class TicketNotFoundException extends RuntimeException{

    TicketNotFoundException(Long id) {
        super("Could not find ticket " + id);
    }
}

