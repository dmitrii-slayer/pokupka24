INSERT INTO users (user_id, username, first_name, last_name, email, birth_date)
VALUES ('d9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 'solo322', 'Naruto', 'Uzumaki', 'naruto@konoha.net', '1990-10-10');
INSERT INTO users (user_id, username, first_name, last_name, email, birth_date)
VALUES ('5e8d25f0-f580-4252-a9dc-f9be4ea9fbae', 'solo322', 'Sasuke', 'Uchiha', 'sasuke@konoha.net', '1990-07-12');
--VALUES (random_uuid(), 'solo322', 'Naruto', 'Uzumaki', 'naruto@konoha.net', '1990-10-10');
--INSERT INTO user_account (account_id, user_id, balance, is_active)
--INSERT INTO user_account VALUES (random_uuid(), 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 540.23, true);
INSERT INTO user_account VALUES ('b2d2918b-3464-4759-9fa8-a027d457fa6d', 'd9f415bf-4eb1-4dca-bbc6-ab1359c6893e', 16438.82, true);
INSERT INTO user_account VALUES ('5c6d9c25-c4bc-4ce1-a018-bde4c06f9986', '5e8d25f0-f580-4252-a9dc-f9be4ea9fbae', 2376.35, true);

--INSERT INTO product (product_id, title, description, price)
INSERT INTO product VALUES ('f362a52f-d038-4802-ba83-6a1216210898', 'Goose Farmer Simulator 2024', '10/10 - IGN', 499.99);
INSERT INTO product VALUES ('d392414c-0d9f-49e6-8ecc-72e79c453144', 'Call of Honor 7 - Standard Edition', 'Base game', 699.99);
INSERT INTO product VALUES ('10c717d1-a1eb-4a2b-a78f-71676c423e33', 'Call of Honor 7 - Premium Edition', 'Base game + 2 skins', 849.99);
INSERT INTO product VALUES ('a4ff4372-f292-4aec-a4c4-abcbb01a4ead', 'Call of Honor 7 - Gold Edition', 'Base game + 2 skins + DLC', 1499.99);

--INSERT INTO purchase (purchase_id, account_id, created_at)
INSERT INTO purchase VALUES ('8243784f-e85f-47f8-be23-f9a45eb198ac', '5c6d9c25-c4bc-4ce1-a018-bde4c06f9986', CURRENT_TIMESTAMP());
INSERT INTO purchase VALUES ('2e12b8bf-48d8-458d-95bb-7e78db89f3ee', '5c6d9c25-c4bc-4ce1-a018-bde4c06f9986', CURRENT_TIMESTAMP());
INSERT INTO purchase VALUES ('7e26b023-0b5e-41bc-8669-519da588a764', 'b2d2918b-3464-4759-9fa8-a027d457fa6d', CURRENT_TIMESTAMP());

--INSERT INTO purchase_detail (id, purchase_id, product_id)
INSERT INTO purchase_detail VALUES (random_uuid(), '8243784f-e85f-47f8-be23-f9a45eb198ac', 'f362a52f-d038-4802-ba83-6a1216210898');
INSERT INTO purchase_detail VALUES (random_uuid(), '2e12b8bf-48d8-458d-95bb-7e78db89f3ee', 'd392414c-0d9f-49e6-8ecc-72e79c453144');
INSERT INTO purchase_detail VALUES (random_uuid(), '7e26b023-0b5e-41bc-8669-519da588a764', 'f362a52f-d038-4802-ba83-6a1216210898');