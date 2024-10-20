CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       UNIQUE (username),
                       UNIQUE (email)
);

CREATE TABLE category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          user_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              amount DOUBLE PRECISION NOT NULL,
                              date TIMESTAMP NOT NULL,
                              description VARCHAR(255),
                              transaction_type VARCHAR(50),
                              category_id BIGINT,
                              user_id BIGINT,
                              FOREIGN KEY (category_id) REFERENCES category(id),
                              FOREIGN KEY (user_id) REFERENCES users(id)
);