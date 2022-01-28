package com.randal_annus.bookclub.business.service;

import com.randal_annus.bookclub.business.model.AuthorInfo;
import com.randal_annus.bookclub.business.model.BookInfo;
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

    @Transactional(readOnly = true)
    public List<BookInfo> findAllBooks() {
        var bookInfos = new ArrayList<BookInfo>();
        bookRepository.findAll().forEach(book -> bookInfos.add(new BookInfo(book)));
        fillAuthorDetails(bookInfos);
        return bookInfos;
    }

    @Transactional(readOnly = true)
    public BookInfo findBookById(long bookId) throws NoSuchElementException {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isEmpty()) throw new NoSuchElementException();
        var bookInfo = new BookInfo(optional.get());
        fillAuthorDetails(bookInfo);
        return bookInfo;
    }

    @Transactional
    public List<BookInfo> findBooksByAuthor(long authorId) throws NoSuchElementException {
        if (!authorRepository.existsByAuthorId(authorId)) throw new NoSuchElementException();
        var bookInfos = bookRepository.findByAuthorId(authorId).stream().map(BookInfo::new).toList();
        fillAuthorDetails(bookInfos);
        return bookInfos;
    }

    /**
     * Create a new book resource.
     * The {@code bookId} field of the provided {@link BookInfo} is ignored.
     * @param bookInfo a book.
     * @return the created book with an {@code id} field.
     */
    @Transactional
    public BookInfo createBook(BookInfo bookInfo) {
        bookInfo.setBookId(null);
        var book = bookInfo.toBook();
        bookRepository.save(book);
        bookInfo = new BookInfo(book);
        fillAuthorDetails(bookInfo);
        return bookInfo;
    }

    /**
     * Update an existing book resource.
     * @param bookInfo a book to be updated.
     * @throws NoSuchElementException If a book resource with the {@code bookId} does not exist.
     */
    @Transactional
    public void updateBook(BookInfo bookInfo) throws NoSuchElementException {
        if (bookInfo.getBookId() == null) throw new IllegalArgumentException();
        var optional = bookRepository.findById(bookInfo.getBookId());
        if (optional.isEmpty()) throw new NoSuchElementException();
        bookRepository.save(bookInfo.toBook());
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
    public List<AuthorInfo> findAllAuthors() {
        var authorInfos = new ArrayList<AuthorInfo>();
        authorRepository.findAll().forEach(author -> authorInfos.add(new AuthorInfo(author)));
        return authorInfos;
    }

    public AuthorInfo findAuthorById(long authorId) throws NoSuchElementException {
        Optional<Author> optional = authorRepository.findById(authorId);
        if (optional.isPresent()) {
            return new AuthorInfo(optional.get());
        }
        throw new NoSuchElementException();
    }

    @Transactional
    public List<AuthorInfo> findAuthorsByIds(Iterable<Long> authorIds) {
        Iterable<Author> authors = authorRepository.findAllById(authorIds);
        var authorInfos = new ArrayList<AuthorInfo>();
        authors.forEach(author -> authorInfos.add(new AuthorInfo(author)));
        return authorInfos;
    }

    /**
     * Create a new author resource.
     * The {@code authorId} field of the provided {@link AuthorInfo} is ignored.
     * @param authorInfo an author.
     * @return the created author with an {@code id} field.
     */
    @Transactional
    public AuthorInfo createAuthor(AuthorInfo authorInfo) {
        authorInfo.setAuthorId(null);
        var author = authorInfo.toAuthor();
        authorRepository.save(author);
        return new AuthorInfo(author);
    }

    /**
     * Update an existing author resource.
     * @param authorInfo the author to be updated.
     * @throws NoSuchElementException If an author resource with the {@code bookId} does not exist.
     */
    @Transactional
    public void updateAuthor(AuthorInfo authorInfo) throws NoSuchElementException {
        if (authorInfo.getAuthorId() == null) throw new IllegalArgumentException();
        var optional = authorRepository.findById(authorInfo.getAuthorId());
        if (optional.isEmpty()) throw new NoSuchElementException();
        authorRepository.save(authorInfo.toAuthor());
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

    /**
     * See {@link #fillAuthorDetails(Iterable, Iterable) fillAuthorDetails(bookInfos, authorInfos)}.
     * Looks up the author if the book has an authorId.
     */
    private void fillAuthorDetails(BookInfo bookInfo) {
        fillAuthorDetails(Set.of(bookInfo));
    }

    /**
     * See {@link #fillAuthorDetails(Iterable, Iterable) fillAuthorDetails(bookInfos, authorInfos)}.
     * Looks up the authors for all books that have authorIds.
     */
    private void fillAuthorDetails(Iterable<BookInfo> bookInfos) {
        var authorIds = new HashSet<Long>();
        bookInfos.forEach(bookInfo -> authorIds.add(bookInfo.getAuthorId()));
        fillAuthorDetails(bookInfos, findAuthorsByIds(authorIds));
    }

    /**
     * Sets the author name for each book.
     * Author name is only set if the corresponding author is found in {@code authorInfos}.
     * @param bookInfos books that need author details.
     * @param authorInfos authors of the books.
     */
    private void fillAuthorDetails(Iterable<BookInfo> bookInfos, Iterable<AuthorInfo> authorInfos) {
        var nameLookup = new HashMap<Long, String>();
        authorInfos.forEach(ai -> nameLookup.put(ai.getAuthorId(), ai.getName()));
        for (var bookInfo : bookInfos) {
            if (bookInfo.getAuthorId() == null) continue;
            var name = nameLookup.get(bookInfo.getAuthorId());
            if (name != null && name.isBlank()) name = null;
            bookInfo.setAuthorName(name);
        }
    }
}
