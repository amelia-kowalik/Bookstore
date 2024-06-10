package com.example.Bookstore.service;

import com.example.Bookstore.model.*;
import com.example.Bookstore.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Transactional
    public Order submitOrder() throws InsufficientResourcesException {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();

        Order order = new Order();
        order.setDate(new Date());
        order.setStatus(Order.OrderStatus.SUBMITTED);
        order.setUser(user);

        for (CartItem cartItem : cart.getItems()) {
            Book book = cartItem.getBook();
            int requestedQuantity = cartItem.getQuantity();

            if(book.getQuantity() < requestedQuantity){
                throw new InsufficientResourcesException("Insufficient quantity for book: " + book.getTitle());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());

            order.getItems().add(orderItem);

            book.setQuantity(book.getQuantity() - requestedQuantity);
        }

        cart.getItems().clear();
        cartService.saveCart(cart);

        return orderRepository.save(order);
    }


    @Transactional
    public Order getOrder(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public List<Order> getOrders(){
       User user = userService.getCurrentUser();
       return user.getOrders();
    }

    @Transactional
    public List<Order> getAllUserOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public void changeOrderStatus(Long orderId, Order.OrderStatus newStatus) {
        Order order = getOrder(orderId);
        order.setStatus(newStatus);
        saveOrder(order);
    }
    @Transactional
    public void saveOrder(Order order){
        orderRepository.save(order);
    }
}
