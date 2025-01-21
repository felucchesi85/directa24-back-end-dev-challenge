DROP TABLE IF EXISTS MOVIEENTITY;

CREATE TABLE MOVIEENTITY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    movie_year VARCHAR(4),
    rated VARCHAR(20),
    released VARCHAR(20),
    runtime VARCHAR(20),
    genre VARCHAR(100),
    director VARCHAR(100),
    writer VARCHAR(255),
    actors VARCHAR(255)
);

SHOW TABLES;

INSERT INTO MOVIEENTITY (id, title, director, year) VALUES (1, 'The Shawshank Redemption', 'Frank Darabont', 1994);
INSERT INTO MOVIEENTITY (id, title, director, year) VALUES (2, 'Inception', 'Christopher Nolan', 2010);


CREATE INDEX idx_movie_director ON movieentity (director);