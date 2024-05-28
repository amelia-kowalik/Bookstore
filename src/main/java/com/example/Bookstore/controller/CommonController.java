package com.example.Bookstore.controller;

import com.example.Bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

    @Autowired
    BookService bookService;

    @GetMapping({"/home"})
    public String home() {
        return "home";
    }

    @RequestMapping(path = {"/main","/","/index"}, method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("books",this.bookService.getAll());
        return "main";
    }

    @RequestMapping(path = {"/adminmain"}, method = RequestMethod.GET)
    public String adminmain(Model model){
        model.addAttribute("books",this.bookService.getAll());
        return "adminmain";
    }
}
