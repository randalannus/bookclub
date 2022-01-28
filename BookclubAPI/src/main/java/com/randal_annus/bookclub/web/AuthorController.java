package com.randal_annus.bookclub.web;

import com.randal_annus.bookclub.business.model.AuthorInfo;
import com.randal_annus.bookclub.business.service.LiteratureService;
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
    public List<AuthorInfo> getAllAuthors() {
        return literatureService.findAllAuthors();
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<AuthorInfo> getAuthor(@PathVariable long authorId) {
        try {
            return ResponseEntity.ok(literatureService.findAuthorById(authorId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorInfo> createAuthor(@RequestBody AuthorInfo authorInfo) {
        authorInfo = literatureService.createAuthor(authorInfo);
        return ResponseEntity.ok(authorInfo);
    }

    @PutMapping("/authors")
    public ResponseEntity<AuthorInfo> updateAuthor(@RequestBody AuthorInfo authorInfo) {
        try {
            literatureService.updateAuthor(authorInfo);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authorInfo);
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
