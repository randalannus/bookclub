package com.randal_annus.bookclub.data.repository;

import com.randal_annus.bookclub.data.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    boolean existsByAuthorId(Long authorId);
}
