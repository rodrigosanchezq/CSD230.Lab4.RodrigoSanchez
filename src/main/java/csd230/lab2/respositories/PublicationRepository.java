package csd230.lab2.respositories;

import csd230.lab2.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByTitle(String title);
}