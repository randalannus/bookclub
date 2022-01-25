package com.randal_annus.bookclub.business.service;

import com.randal_annus.bookclub.data.entity.Book;
import com.randal_annus.bookclub.data.repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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

    /**
     * Create a new book resource.
     * The {@code bookId} field of the provided {@code Book} is ignored and set to a new unique id in place.
     * @param book a book.
     */
    public void create(Book book) {
        book.setBookId(null);
        bookRepository.save(book);
    }

    /**
     * Update an existing book resource.
     * @param book a book to be updated.
     * @throws NoSuchElementException If a book resource with the {@code bookId} does not exist.
     */
    public void update(Book book) throws NoSuchElementException {
        var optional = bookRepository.findById(book.getBookId());
        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        }
        bookRepository.save(book);
    }

    /**
     * Delete an existing book resource.
     * @param bookId id of the deleted book.
     * @throws NoSuchElementException if the resource with this id doesn't exist.
     */
    public void delete(long bookId) throws NoSuchElementException {
        try {
            bookRepository.deleteById(bookId);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        }
    }
}
