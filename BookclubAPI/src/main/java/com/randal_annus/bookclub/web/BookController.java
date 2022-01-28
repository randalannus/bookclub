package com.randal_annus.bookclub.web;

import com.randal_annus.bookclub.business.model.BookInfo;
import com.randal_annus.bookclub.business.service.LiteratureService;
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
    public List<BookInfo> getBooks() {
        return literatureService.findAllBooks();
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookInfo> getBook(@PathVariable long bookId) {
        try {
            return ResponseEntity.ok(literatureService.findBookById(bookId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<BookInfo> createBook(@RequestBody BookInfo bookInfo) {
        bookInfo = literatureService.createBook(bookInfo);
        return ResponseEntity.ok(bookInfo);
    }

    @PutMapping("/books")
    public ResponseEntity<BookInfo> updateBook(@RequestBody BookInfo bookInfo) {
        try {
            literatureService.updateBook(bookInfo);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (java.lang.IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookInfo);
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
