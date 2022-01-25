CREATE TABLE books (
  book_id BIGSERIAL PRIMARY KEY,
  book_name VARCHAR(255),
  description VARCHAR(2048),
  author_id BIGINT
);
