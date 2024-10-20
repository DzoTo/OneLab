INSERT INTO users (username, password, email, created_at)
VALUES ('DzoTo', 'qwe123', 'test@gmail.com', CURRENT_TIMESTAMP);


INSERT INTO transactions (amount, date, description, transaction_type, user_id, category_id)
VALUES (500, CURRENT_TIMESTAMP, 'Payment for ice cream', 'EXPENSE', 1, null);


INSERT INTO category (name, user_id)
VALUES ( 'Food', 1);