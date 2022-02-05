package com.randal_annus.bookclub.web;

import com.randal_annus.bookclub.business.service.LiteratureService;
import com.randal_annus.bookclub.data.entity.Author;
import com.randal_annus.bookclub.data.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AuthorController {
    private final LiteratureService literatureService;

    public AuthorController(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @GetMapping("/authors")
    public Iterable<Author> getAllAuthors() {
        return literatureService.findAllAuthors();
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable long authorId) {
        try {
            return ResponseEntity.ok(literatureService.findAuthorById(authorId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/authors/{authorId}/books")
    public ResponseEntity<Iterable<Book>> getBooksByAuthor(@PathVariable long authorId) {
        try {
            return ResponseEntity.ok(literatureService.findBooksByAuthor(authorId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        literatureService.createAuthor(author);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/authors")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        try {
            literatureService.updateAuthor(author);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("authors/{authorId}")
    public ResponseEntity<Void> deleteBook(@PathVariable long authorId) {
        try {
            literatureService.deleteAuthor(authorId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
