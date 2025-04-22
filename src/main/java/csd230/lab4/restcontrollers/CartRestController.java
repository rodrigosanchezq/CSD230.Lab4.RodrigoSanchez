package csd230.lab4.restcontrollers;


import csd230.lab4.entities.Cart;
import csd230.lab4.respositories.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("rest/cart")
public class CartRestController {

     private final CartRepository cartRepository;

     public CartRestController(CartRepository cartRepository) {
         this.cartRepository = cartRepository;
     }

     @CrossOrigin
     @GetMapping()
     List<Cart> all() {
         List<Cart> all = (List<Cart>) cartRepository.findAll();
         return all;
     }

     @GetMapping
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
        public Cart getCart(@PathVariable int id) {
            Cart cart = cartRepository.findById(id);
            return cart;
        }

        @PostMapping()
        Cart newCart(@RequestBody Cart newCart) {
            return cartRepository.save(newCart);
        }

        @DeleteMapping("/{id}")
        void deleteCart(@PathVariable Long id) {
            cartRepository.deleteById(id);
        }
        @PostMapping("/remove-cart")
        public String remove_cartSubmit(@RequestParam(value = "cartItemId", required = false) Integer id) {
            cartRepository.deleteById(Long.valueOf(id));
            return "redirect:/cart";
        }
        @PostMapping("/add-cart")
        public String cartSubmit(@ModelAttribute Cart cart) {
            cartRepository.save(cart);
            return "redirect:/cart";
        }

        @GetMapping("/add-cart")
        public String cartForm() {
            return "add-cart";
        }
        @PostMapping("/selection")
        public String processSelection(@RequestParam("selectedCarts") List<Integer> selectedCartIds) {
            // Process the selected cart list here...
            System.out.println(selectedCartIds);
            for (Integer id : selectedCartIds) {
                Cart cart = cartRepository.findById(id);
                cartRepository.delete(cart);
            }
            return "redirect:/cart";
        }
        @GetMapping("/checkout")
        public String checkout(@PathVariable Integer id) {
            Cart cart = cartRepository.findById(id);
            cartRepository.delete(cart);
            return "redirect:/cart";
        }
        @GetMapping("/delete-cart")
        public String delete_cart(@PathVariable Integer id) {
            Cart cart = cartRepository.findById(id);
            cartRepository.delete(cart);
            return "redirect:/cart";
        }
}

