INSERT INTO users (user_id, username, first_name, last_name, email, birth_date)
VALUES ('d9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 'solo322', 'Naruto', 'Uzumaki', 'naruto@konoha.net', '1990-10-10');
--VALUES (random_uuid(), 'solo322', 'Naruto', 'Uzumaki', 'naruto@konoha.net', '1990-10-10');
--INSERT INTO user_account (account_id, user_id, balance, is_active)
INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 540.23, true);
INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 16438.82, true);
INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 2376.35, true);

--INSERT INTO product (product_id, title, description, price)
INSERT INTO product VALUES ('f362a52f-d038-4802-ba83-6a1216210898', 'Goose Farmer Simulator 2024', '10/10 - IGN', 499.99);
INSERT INTO product VALUES ('d392414c-0d9f-49e6-8ecc-72e79c453144', 'Call of Honor 7 - Standard Edition', 'Base game', 699.99);
INSERT INTO product VALUES ('10c717d1-a1eb-4a2b-a78f-71676c423e33', 'Call of Honor 7 - Premium Edition', 'Base game + 2 skins', 849.99);
INSERT INTO product VALUES ('a4ff4372-f292-4aec-a4c4-abcbb01a4ead', 'Call of Honor 7 - Gold Edition', 'Base game + 2 skins + DLC', 1499.99);