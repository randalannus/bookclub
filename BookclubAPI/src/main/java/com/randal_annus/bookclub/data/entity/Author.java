package com.randal_annus.bookclub.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId")
    private Long authorId;
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "death_date")
    private Date deathDate;

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
