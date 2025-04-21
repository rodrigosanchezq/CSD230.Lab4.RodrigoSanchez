package csd230.lab3.respositories;


import csd230.lab3.entities.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    void removeById(Long id);
}
