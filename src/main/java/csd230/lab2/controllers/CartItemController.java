package csd230.lab2.controllers;


import csd230.lab2.entities.CartItem;
import csd230.lab2.respositories.CartItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class CartItemController {

    private final CartItemRepository cartItemRepository;

    public CartItemController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping
    public String cartItems(Model model) {
        model.addAttribute("cartItems", cartItemRepository.findAll());
        return "cart-items";
    }

    @GetMapping("/add-item")
    public String cartItemForm(Model model) {
        model.addAttribute("cartItem", new CartItem());
        return "add-item";
    }

    @PostMapping("/add-item")
    public String cartItemSubmit(@ModelAttribute CartItem cartItem, Model model) {
        model.addAttribute("cartItem", cartItem);
        cartItemRepository.save(cartItem);
        model.addAttribute("cartItems", cartItemRepository.findAll());
        return "redirect:/cart-items";
    }

    @PostMapping("/remove-item")
    public String removeCartItemSubmit(@RequestParam(value = "cartItemId", required = false) Integer id, Model model) {
        cartItemRepository.removeById(Long.valueOf(id));
        return "redirect:/cart-items";
    }
}