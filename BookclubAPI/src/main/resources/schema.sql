CREATE TABLE books (
  book_id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255),
  description VARCHAR(2048),
  author_id BIGINT
);
