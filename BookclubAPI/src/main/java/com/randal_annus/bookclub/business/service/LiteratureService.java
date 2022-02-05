package com.randal_annus.bookclub.business.service;

import com.randal_annus.bookclub.data.entity.Author;
import com.randal_annus.bookclub.data.entity.Book;
import com.randal_annus.bookclub.data.repository.AuthorRepository;
import com.randal_annus.bookclub.data.repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LiteratureService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public LiteratureService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBookById(long bookId) throws NoSuchElementException {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isEmpty()) throw new NoSuchElementException();
        return optional.get();
    }

    @Transactional
    public Iterable<Book> findBooksByAuthor(long authorId) throws NoSuchElementException {
        if (!authorRepository.existsByAuthorId(authorId)) throw new NoSuchElementException();
        return bookRepository.findByAuthor_AuthorId(authorId);
    }

    /**
     * Create a new book resource.
     * The {@code bookId} field of the book is ignored and a new value is generated for it.
     * @param book a book.
     */
    @Transactional
    public void createBook(Book book) {
        book.setBookId(null);
        bookRepository.save(book);
    }

    /**
     * Update an existing book resource.
     * @param book a book to be updated.
     * @throws NoSuchElementException If a book resource with the {@code bookId} does not exist.
     * @throws IllegalArgumentException If the bookId is {@code null}.
     */
    @Transactional
    public void updateBook(Book book) throws NoSuchElementException {
        if (book.getBookId() == null) throw new IllegalArgumentException();
        Optional<Book> optional = bookRepository.findById(book.getBookId());
        if (optional.isEmpty()) throw new NoSuchElementException();
        bookRepository.save(book);
    }

    /**
     * Delete an existing book resource.
     * @param bookId id of the deleted book.
     * @throws NoSuchElementException if the resource with this id doesn't exist.
     */
    public void deleteBook(long bookId) throws NoSuchElementException {
        try {
            bookRepository.deleteById(bookId);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(long authorId) throws NoSuchElementException {
        Optional<Author> optional = authorRepository.findById(authorId);
        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optional.get();
    }

    @Transactional
    public Iterable<Author> findAuthorsByIds(Iterable<Long> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

    /**
     * Create a new author resource.
     * The {@code authorId} field is ignored and a new id is generated.
     * @param author an author.
     */
    @Transactional
    public void createAuthor(Author author) {
        author.setAuthorId(null);
        authorRepository.save(author);
    }

    /**
     * Update an existing author resource.
     * @param author the author to be updated.
     * @throws NoSuchElementException If an author resource with the {@code bookId} does not exist.
     * @throws IllegalArgumentException If the authorId is {@code null}.
     */
    @Transactional
    public void updateAuthor(Author author) throws NoSuchElementException {
        if (author.getAuthorId() == null) throw new IllegalArgumentException();
        var optional = authorRepository.findById(author.getAuthorId());
        if (optional.isEmpty()) throw new NoSuchElementException();
        authorRepository.save(author);
    }

    /**
     * Delete an existing author resource.
     * @param authorId id of the deleted author.
     * @throws NoSuchElementException if the resource with this id doesn't exist.
     */
    public void deleteAuthor(long authorId) throws NoSuchElementException {
        try {
            authorRepository.deleteById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        }
    }
}
