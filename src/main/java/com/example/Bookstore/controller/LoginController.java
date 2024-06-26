package com.example.Bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "message", required = false) String message,
                        Model model) {
        if ( message!=null){
            model.addAttribute("message",message);
        }
        if (error != null) {
            model.addAttribute("message", "Wrong username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out!");
        }
        return "login";
    }
}
