DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name) VALUES ('Restaurant 1'),('Restaurant 2'), ('Restaurant 3');


INSERT INTO meals (date, description, price, restaurant_id) VALUES
  ('2018-05-05', 'first course 1', 500.00, 100002),
  ('2018-05-05', 'garnish 1', 300.00, 100002),
  ('2018-05-05', 'second course 1', 700.00, 100002),
  ('2018-05-06', 'first course 2', 500.00, 100003),
  ('2018-05-06', 'garnish 2', 100.00, 100003),
  ('2018-05-06', 'second course 2', 500.00, 100003),
  ('2018-05-07', 'first course 3', 200.00, 100004),
  ('2018-05-07', 'garnish 3', 1000.00, 100004),
  ('2018-05-07', 'second course 3', 300.00, 100004);

INSERT INTO votes (restaurant_id, user_id, date) VALUES
  (100003, 100000, '2018-05-06'),
  (100003, 100001, '2018-05-07'),
  (100004, 100000, '2018-05-07'),
  (100003, 100001, '2018-05-08'),
  (100002, 100000, '2018-05-08');