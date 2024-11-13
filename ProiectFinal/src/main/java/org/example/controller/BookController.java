package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        return  new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){ return service.save(book);}

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Integer id){ service.delete(id);}

    @PutMapping
    public Book updateBook(@RequestBody Book book){return service.update(book);}
}
