package csd230.lab4.restcontrollers;

public class CartNotFoundException extends RuntimeException{
        CartNotFoundException(Long id) { super("Could not find cart " + id); }
    }
