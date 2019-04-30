DELETE FROM user_roles;
DELETE FROM costs;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, sum_per_day)
VALUES ('User', 'user@yandex.ru', 'password', 1000);

INSERT INTO users (name, email, password, sum_per_day)
VALUES ('Admin', 'admin@gmail.com', 'admin', 2000);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO costs (date_time, description, price, user_id) VALUES
  ('2018-07-21 10:00:00', 'Продукты', 800, 100000),
  ('2018-07-21 13:00:00', 'Продукты', 100, 100000),
  ('2018-07-22 11:30:00', 'Кафе', 150, 100000),
  ('2018-07-22 15:30:00', 'Продукты', 70, 100000),
  ('2018-07-22 17:00:00', 'Аренда жилья', 15000, 100000),
  ('2018-07-25 19:00:00', 'Продукты', 450, 100000),
  ('2018-08-11 11:00:00', 'fat', 150, 100001),
  ('2018-08-11 11:30:00', 'tea tree oil', 100, 100001);
