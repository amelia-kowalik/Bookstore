package com.example.Bookstore.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void addItem(Book book, int quantity) {
        CartItem cartItem = new CartItem(this, book, quantity);
        for (CartItem i : items) {
            if (i.getBook().equals(book)) {
                i.setQuantity(i.getQuantity() + quantity);
                return;
            }
        }
        items.add(cartItem);
    }


    public void removeItem(Book book) {
        for (CartItem i : items) {
            if (i.getBook().equals(book)) {
                int newQuantity = i.getQuantity() - 1;
                if (newQuantity < 1) {
                    items.remove(i);
                } else {
                    i.setQuantity(newQuantity);
                }
                break;
            }
        }
    }
}

