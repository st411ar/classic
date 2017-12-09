-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS papers;

CREATE DATABASE papers 
    DEFAULT CHARACTER SET 'utf8' 
    DEFAULT COLLATE 'utf8_unicode_ci';

USE papers;

-- ---
-- Table 'users'
-- Users may get paper books for a period
-- ---

DROP TABLE IF EXISTS users;
    
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = INNODB;

-- ---
-- Table 'books'
-- Books may be got by users
-- ---

DROP TABLE IF EXISTS books;
    
CREATE TABLE books (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = INNODB;

-- ---
-- Table 'orders'
-- Orders display which books users have
-- ---

DROP TABLE IF EXISTS orders;
    
CREATE TABLE orders (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  book_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (book_id) REFERENCES books(id)
) ENGINE = INNODB;

-- ---
-- Test Data
-- ---

INSERT INTO users (name) VALUES ('Эдгар Рыбак');
INSERT INTO users (name) VALUES ('Татьяна Ковальчук');
INSERT INTO users (name) VALUES ('Екатерина Басюк');
INSERT INTO users (name) VALUES ('Виталий Киселёв');

INSERT INTO books (name) VALUES ('Философия JAVA');
INSERT INTO books (name) VALUES ('Совершенный код');
INSERT INTO books (name) VALUES ('Экстремальное программирование');
INSERT INTO books (name) VALUES ('Java. Библиотека профессионала. Т.1');
INSERT INTO books (name) VALUES ('Java. Библиотека профессионала. Т.2');

INSERT INTO orders (user_id, book_id) VALUES (1, 1);
INSERT INTO orders (user_id, book_id) VALUES (2, 2);
INSERT INTO orders (user_id, book_id) VALUES (3, 3);
INSERT INTO orders (user_id, book_id) VALUES (4, 4);
INSERT INTO orders (user_id, book_id) VALUES (4, 5);
