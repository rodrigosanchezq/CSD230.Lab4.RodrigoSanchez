package csd230.lab4.respositories;


import csd230.lab4.entities.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    void removeById(Long id);
}
