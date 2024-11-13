package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.entity.User;
import org.example.repository.BookRepo;
import org.example.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;

    public User save(@RequestBody User user) {
        if (user.getId() != null) {
            throw new RuntimeException("Id must be null");
        }
        try {
            return repo.save(user);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Entity must not be null");
        }
    }

    public List<User> findAll() {
        List<User> users = repo.findAll();

        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found");
        }
        return users;
    }

    public void delete(Integer id) {
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        repo.deleteById(id);
    }
    public User update(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("Id cannot be null");
        }
        try {
            return repo.save(user);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("User must not be null");
        }
    }
}
