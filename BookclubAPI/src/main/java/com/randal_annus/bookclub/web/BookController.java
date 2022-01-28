package com.randal_annus.bookclub.web;

import com.randal_annus.bookclub.business.model.BookInfo;
import com.randal_annus.bookclub.business.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookInfo> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public BookInfo getBook(@PathVariable long bookId) {
        return bookService.findById(bookId);
    }

    @PostMapping("/books")
    public ResponseEntity<BookInfo> createBook(@RequestBody BookInfo bookInfo) {
        bookService.create(bookInfo);
        return ResponseEntity.ok(bookInfo);
    }

    @PutMapping("/books")
    public ResponseEntity<BookInfo> updateBook(@RequestBody BookInfo bookInfo) {
        try {
            bookService.update(bookInfo);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookInfo);
    }

    @DeleteMapping("books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable long bookId) {
        try {
            bookService.delete(bookId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
