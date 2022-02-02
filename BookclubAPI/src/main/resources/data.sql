INSERT INTO authors (name, birth_date) VALUES ('Barack Obama', '1961-08-04');

INSERT INTO books (title, description, author_id) VALUES ('A Promised Land', 'This is a book', (SELECT author_id FROM authors WHERE name='Barack Obama'));