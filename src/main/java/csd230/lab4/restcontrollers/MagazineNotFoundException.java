package csd230.lab4.restcontrollers;


public class MagazineNotFoundException extends RuntimeException{

    MagazineNotFoundException(Long id) {
        super("Could not find magazine " + id);
    }
}
