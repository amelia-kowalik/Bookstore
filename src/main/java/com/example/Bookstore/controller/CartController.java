package com.example.Bookstore.controller;


import com.example.Bookstore.model.Cart;
import com.example.Bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/add/{bookId}/{quantity}")
    public String addToCart (@PathVariable int bookId, @PathVariable int quantity){
        cartService.addToCart(bookId, quantity);
        return "redirect:/main";
    }


    @GetMapping
    public String getCart(Model model){
        Cart cart = cartService.getCart();
        model.addAttribute("cart",cart);
        return "cart";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable int bookId){
        cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }
}



