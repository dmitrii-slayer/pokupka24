CREATE TABLE users (
  user_id UUID NOT NULL,
   username VARCHAR(255),
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   birth_date date,
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE user_account (
  account_id UUID NOT NULL,
   user_id UUID NOT NULL,
   balance NUMERIC(20,2) NOT NULL,
   is_active BOOLEAN NOT NULL,
   CONSTRAINT pk_user_account PRIMARY KEY (account_id),
   CONSTRAINT fk_user_account_on_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

--ALTER TABLE user_account ADD CONSTRAINT FK_USER_ACCOUNT_ON_USER
--FOREIGN KEY (user_id) REFERENCES users (user_id);

CREATE TABLE product (
  product_id UUID NOT NULL,
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   price NUMERIC(12,2),
   CONSTRAINT pk_product PRIMARY KEY (product_id)
);

CREATE TABLE purchase (
  purchase_id UUID NOT NULL,
   account_id UUID NOT NULL,
   product_id UUID NOT NULL,
   created_at TIMESTAMP NOT NULL,
   CONSTRAINT pk_purchase PRIMARY KEY (purchase_id)
);

ALTER TABLE purchase ADD CONSTRAINT FK_PURCHASE_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES user_account (account_id);

ALTER TABLE purchase ADD CONSTRAINT FK_PURCHASE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);