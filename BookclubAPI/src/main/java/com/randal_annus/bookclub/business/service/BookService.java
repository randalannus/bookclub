package com.randal_annus.bookclub.business.service;

import com.randal_annus.bookclub.business.model.BookInfo;
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

    public List<BookInfo> findAll() {
        var bookInfos = new ArrayList<BookInfo>();
        bookRepository.findAll().forEach(book -> {
            var bookInfo = new BookInfo(book);
            bookInfos.add(bookInfo);
        });
        return bookInfos;
    }

    public BookInfo findById(long bookId) throws NoSuchElementException {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            return new BookInfo(optional.get());
        }
        throw new NoSuchElementException();
    }

    public List<BookInfo> findByAuthor(long authorId) {
        return bookRepository.findByAuthorId(authorId).stream().map(BookInfo::new).toList();
    }

    /**
     * Create a new book resource.
     * The {@code bookId} field of the provided {@link BookInfo} is ignored and set to a new unique id in place.
     * @param bookInfo a book.
     */
    public void create(BookInfo bookInfo) {
        bookInfo.setBookId(null);
        bookRepository.save(bookInfo.toBook());
    }

    /**
     * Update an existing book resource.
     * @param bookInfo a book to be updated.
     * @throws NoSuchElementException If a book resource with the {@code bookId} does not exist.
     */
    public void update(BookInfo bookInfo) throws NoSuchElementException {
        var optional = bookRepository.findById(bookInfo.getBookId());
        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        }
        bookRepository.save(bookInfo.toBook());
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
