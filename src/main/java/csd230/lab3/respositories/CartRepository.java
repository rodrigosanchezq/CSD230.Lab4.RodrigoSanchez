package csd230.lab3.respositories;

import csd230.lab3.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findById(long id);
}
