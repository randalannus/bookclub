CREATE TABLE books (
  book_id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255),
  description VARCHAR(2048),
  author_id BIGINT
);

CREATE TABLE authors (
  author_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255),
  birth_date DATE,
  death_date DATE
);

ALTER TABLE books ADD FOREIGN KEY (author_id) REFERENCES authors(author_id);