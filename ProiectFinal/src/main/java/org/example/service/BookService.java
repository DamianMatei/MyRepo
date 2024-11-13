package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.repository.BookRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo repo;

    public Book save(@RequestBody Book book) {
        if (book.getId() != null) {
            throw new RuntimeException("Id must be null");
        }
        try {
            return repo.save(book);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Entity must not be null");
        }
    }

    public List<Book> findAll() {
        List<Book> books = repo.findAll();

        if (books.isEmpty()) {
            throw new EntityNotFoundException("No books found");
        }
        return books;
    }

        public void delete(Integer id) {
            if (id == null) {
                throw new RuntimeException("Id cannot be null");
            }
            repo.deleteById(id);
        }
    public Book update(Book book) {
        if (book.getId() == null) {
            throw new RuntimeException("Id cannot be null");
        }
        try {
            return repo.save(book);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Book must not be null");
        }
    }
    public List<Book> findBooksByAuthor(Long authorId) {
        return repo.findAll().stream()
                .filter(book -> book.getAuthor().getId().equals(authorId))
                .collect(Collectors.toList());
    }
    public List<String> getAllBookTitles() {
        return repo.findAll().stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }
    public List<Book> getBooksSortedByTitle() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }
}
