package com.example.Bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/adminpanel")
    public String adminpanel(){
        return "adminpanel";
    }
}
