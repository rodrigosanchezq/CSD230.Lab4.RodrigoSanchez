package csd230.lab3.respositories;

import csd230.lab3.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByIsbn(String isbn);
    Book findById(long id);

}
