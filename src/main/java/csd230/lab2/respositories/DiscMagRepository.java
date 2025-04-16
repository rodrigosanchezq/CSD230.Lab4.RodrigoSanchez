package csd230.lab2.respositories;


import csd230.lab2.entities.DiscMag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscMagRepository extends JpaRepository<DiscMag, Long> {
    /**
     * Find all DiscMag entities.
     *
     * @return a list of all DiscMag entities
     */
    @Query("SELECT d FROM DiscMag d")
    List<DiscMag> findAll();

    DiscMag findById(long id);
    List<DiscMag> findDiscMagByTitle(String title);
    List<DiscMag> findDiscMagByTitleContaining(String titleContains);

    List<DiscMag> findByPriceBetween(double low, double high);

    @Query ("SELECT d FROM DiscMag d WHERE d.hasDisc = ?1")
    List<DiscMag> findByHasDisc(boolean hasDisc);
}