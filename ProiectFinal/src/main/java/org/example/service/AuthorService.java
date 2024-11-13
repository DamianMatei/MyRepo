package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Author;
import org.example.repository.AuthorRepo;
import org.example.repository.BookRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo repo;

    public List<Author> findAll() {
        List<Author> authors = repo.findAll();

        if (authors.isEmpty()) {
            throw new EntityNotFoundException("No authors found");
        }
        return authors;
    }
    public Author save(@RequestBody Author author) {
        if (author.getId() != null) {
            throw new RuntimeException("Id must be null");
        }
        try {
            return repo.save(author);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Entity nust not be null");
        }
    }
}
