package com.bs.controller;

import com.bs.entity.Book;
import com.bs.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(book.getTitle());
                    existing.setAuthor(book.getAuthor());
                    existing.setPrice(book.getPrice());
                    return repository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "Book deleted";
    }
}
