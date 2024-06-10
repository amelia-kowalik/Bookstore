package com.example.Bookstore.controller;

import com.example.Bookstore.model.Order;
import com.example.Bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public String submitOrder(RedirectAttributes redirectAttributes) {
        try {
            Order order = orderService.submitOrder();
            return "redirect:/order/" + order.getId();
        } catch (InsufficientResourcesException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cart";
        }
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model){
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order",order);
        return "order";
    }

    @GetMapping("/allorders")
    public String getOrders(Model model){
        List<Order> orders = orderService.getOrders();
        model.addAttribute("allorders", orders);
        return "allorders";
    }

    @GetMapping("/alluserorders")
    public String getAllUserOrders(Model model){
        List<Order> orders = orderService.getAllUserOrders();
        model.addAttribute("alluserorders", orders);
        return "alluserorders";
    }

    @PostMapping("/alluserorders/{orderId}/status")
    public String changeOrderStatus(@PathVariable Long orderId, @RequestParam Order.OrderStatus newStatus, RedirectAttributes redirectAttributes) {
        try {
            orderService.changeOrderStatus(orderId, newStatus);
            redirectAttributes.addFlashAttribute("successMessage", "Order status changed successfully.");
        } catch (SecurityException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/order/alluserorders";
    }
}
