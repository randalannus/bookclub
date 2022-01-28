package com.randal_annus.bookclub.business.model;

import com.randal_annus.bookclub.data.entity.Author;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorInfo {
    private Long authorId;
    private String name;
    private Date birthDate;
    private Date deathDate;

    public AuthorInfo() {}

    public AuthorInfo(Author author) {
        this.authorId = author.getAuthorId();
        this.name = author.getName();
        this.birthDate = author.getBirthDate();
        this.deathDate = author.getDeathDate();
    }

    public Author toAuthor() {
        var author = new Author();
        author.setAuthorId(authorId);
        author.setName(name);
        if (birthDate != null) author.setBirthDate(new java.sql.Date(birthDate.getTime()));
        if (deathDate != null) author.setDeathDate(new java.sql.Date(deathDate.getTime()));
        return author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }
}
