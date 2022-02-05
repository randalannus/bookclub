package com.randal_annus.bookclub.data.repository;

import com.randal_annus.bookclub.data.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findByAuthor_AuthorId(Long authorId);



}
