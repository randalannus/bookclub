package com.randal_annus.bookclub.web;

import com.randal_annus.bookclub.business.service.LiteratureService;
import com.randal_annus.bookclub.data.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController {
    private final LiteratureService literatureService;

    public BookController(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return literatureService.findAllBooks();
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable long bookId) {
        try {
            return ResponseEntity.ok(literatureService.findBookById(bookId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        literatureService.createBook(book);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        try {
            literatureService.updateBook(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (java.lang.IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable long bookId) {
        try {
            literatureService.deleteBook(bookId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
