package csd230.lab2;

import csd230.lab2.entities.*;
import csd230.lab2.respositories.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class ApplicationTests {


	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private MagazineRepository magazineRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private DiscMagRepository discMagRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;

	private Book book;
	private Magazine magazine;
	private Ticket ticket;
	private DiscMag discMag;

	@BeforeEach
	void setUp() {
		// Initialize entities for testing
		book = new Book(19.99, 10, "Test Book", "Test Author", 15, "Test Title", "1234567890");
		magazine = new Magazine(15.99, 5, "Test Magazine", "Test Magazine Title", 10, 100, new java.util.Date());
		ticket = new Ticket(95.99, 12, "Test Ticket, Description", "Test Ticket Text");
		discMag = new DiscMag(20.99, "Test DiscMag", 20, 4, new java.util.Date(), true);

		bookRepository.save(book);
		magazineRepository.save(magazine);
		ticketRepository.save(ticket);
		discMagRepository.save(discMag);
	}

	@Test
	void testCreateAndReadBook() {
		// Test Read
		Book fetchedBook = bookRepository.findById(book.getId()).orElse(null);
		assertNotNull(fetchedBook);
		assertEquals(book.getTitle(), fetchedBook.getTitle());
	}

	@Test
	void testCreateAndReadTicket() {
		// Test Read
		Ticket fetchedTicket = ticketRepository.findById(ticket.getId()).orElse(null);
		assertNotNull(fetchedTicket);
		assertEquals(ticket.getText(), fetchedTicket.getText());
	}

	@Test
	void testCreateAndReadMagazine() {
		// Test Read
		Magazine fetchedMagazine = magazineRepository.findById(magazine.getId()).orElse(null);
		assertNotNull(fetchedMagazine);
		assertEquals(magazine.getTitle(), fetchedMagazine.getTitle());
	}

	@Test
	void testCreateAndReadCart() {
		// Test Read
		Cart cart = new Cart();
		cartRepository.save(cart);
		Cart fetchedCart = cartRepository.findById(cart.getId()).orElse(null);
		assertNotNull(fetchedCart);
		assertEquals(cart.getId(), fetchedCart.getId());
	}

	@Test
	void testUpdateBook() {
		// Test Update
		book.setPrice(25.99);
		bookRepository.save(book);

		Book updatedBook = bookRepository.findById(book.getId()).orElse(null);
		assertNotNull(updatedBook);
		assertEquals(25.99, updatedBook.getPrice());
	}

	@Test
	void testUpdateMagazine() {
		// Test Update
		magazine.setPrice(25.99);
		magazineRepository.save(magazine);

		Magazine updatedMagazine = magazineRepository.findById(magazine.getId()).orElse(null);
		assertNotNull(updatedMagazine);
		assertEquals(25.99, updatedMagazine.getPrice());
	}

	@Test
	void testDeleteBook() {
		// Test Delete
		bookRepository.deleteById(book.getId());
		Book deletedBook = bookRepository.findById(book.getId()).orElse(null);
		assertNull(deletedBook);
	}

	@Test
	void testDeleteMagazine() {
		// Test Delete
		magazineRepository.deleteById(magazine.getId());
		Magazine deletedMagazine = magazineRepository.findById(magazine.getId()).orElse(null);
		assertNull(deletedMagazine);
	}
}