package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.entity.User;
import org.example.service.BookService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public User saveUser(@RequestBody User user){ return service.save(user);}

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){ service.delete(id);}

    @PutMapping
    public User updateUser(@RequestBody User user){return service.update(user);}
}

