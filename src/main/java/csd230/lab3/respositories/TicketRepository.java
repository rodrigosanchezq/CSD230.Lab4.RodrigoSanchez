package csd230.lab3.respositories;

import csd230.lab3.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    /**
     * Find tickets with price greater than the specified value.
     */
    @Query ("SELECT t FROM Ticket t WHERE t.price > :price")
    List<Ticket> findByPriceGreaterThan(double price);

    List<Ticket> findByPriceBetween(double price, double price1);

    Ticket findById(long id);

}