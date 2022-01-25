package com.randal_annus.bookclub.data.repository;

import com.randal_annus.bookclub.data.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthorId(long authorId);

}
