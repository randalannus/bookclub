package com.randal_annus.bookclub.business.service;

import com.randal_annus.bookclub.data.entity.Book;
import com.randal_annus.bookclub.data.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        var books = new ArrayList<Book>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book findById(long bookId) throws NoSuchElementException {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NoSuchElementException();
    }

    public List<Book> findByAuthor(long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
