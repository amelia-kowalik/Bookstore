package com.example.Bookstore.service;

import com.example.Bookstore.model.Role;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.RoleRepository;
import com.example.Bookstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public String registerUser(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            return "failure";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName("USER").orElseGet(null);
        if (userRole != null){
            user.getRoles().add(userRole);
        } else {
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
        }

        userRepository.save(user);
        return "succes";
    }
}
