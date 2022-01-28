package com.randal_annus.bookclub.business.model;

import com.randal_annus.bookclub.data.entity.Book;

/**
 * A DTO class for representing all the info about a book.
 * This is different from {@link Book} in the sense that this has extra information about the author
 * while {@code Book} is a one-to-one mapping to the database.
 */
public class BookInfo {
    private Long bookId;
    private Long authorId;
    private String authorName;
    private String title;
    private String description;

    public BookInfo() {
    }

    public BookInfo(Book book) {
        this.bookId = book.getBookId();
        this.authorId = book.getAuthorId();
        this.authorName = null;
        this.title = book.getTitle();
        this.description = book.getDescription();
    }

    /**
     * Convert this to a {@link Book} object for database access.
     * @return a {@link Book} object
     */
    public Book toBook() {
        var book = new Book();
        book.setBookId(this.bookId);
        book.setAuthorId(this.authorId);
        book.setTitle(this.title);
        book.setDescription(this.description);
        return book;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
