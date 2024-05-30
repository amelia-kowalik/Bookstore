package com.example.Bookstore.repository;

import com.example.Bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart,Integer> {
}
