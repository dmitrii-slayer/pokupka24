INSERT INTO users (user_id, username, first_name, last_name, email, birth_date)
VALUES ('d9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 'solo322', 'Naruto', 'Uzumaki', 'naruto@konoha.net', '1990-10-10');
--VALUES (random_uuid(), 'solo322', 'Naruto', 'Uzumaki', 'naruto@konoha.net', '1990-10-10');
--INSERT INTO user_account (account_id, user_id, balance, currency, is_active)
INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 540.23, 'USD', true);
INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 16438.82, 'RUB', true);
INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 2376.35, 'RMB', true);