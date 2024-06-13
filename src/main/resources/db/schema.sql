CREATE TABLE IF NOT EXISTS users (
  user_id UUID NOT NULL,
   username VARCHAR(30) NOT NULL,
   first_name VARCHAR(50) NOT NULL,
   last_name VARCHAR(50) NOT NULL,
   email VARCHAR(100) NOT NULL,
   birth_date date NOT NULL,
   registration_date date NOT NULL DEFAULT CURRENT_DATE,
   CONSTRAINT pk_users PRIMARY KEY (user_id),
   CONSTRAINT uc_email UNIQUE (email);
);

--CREATE TABLE user_account (
--  account_id UUID NOT NULL,
--   user_id UUID NOT NULL,
--   balance NUMERIC(20,2) NOT NULL,
--   is_active BOOLEAN NOT NULL,
--   CONSTRAINT pk_user_account PRIMARY KEY (account_id),
--   CONSTRAINT fk_user_account_on_user FOREIGN KEY (user_id) REFERENCES users (user_id)
--);

CREATE TABLE IF NOT EXISTS user_account (
  account_id UUID NOT NULL,
   user_id UUID NOT NULL,
   balance NUMERIC(12,2) NOT NULL,
   is_active BOOLEAN NOT NULL,
   CONSTRAINT pk_user_account PRIMARY KEY (account_id),
   CONSTRAINT cc_positive_account_balance CHECK (balance >= 0)
);

ALTER TABLE user_account ADD CONSTRAINT uc_user_account_user UNIQUE (user_id);
ALTER TABLE user_account ADD CONSTRAINT FK_USER_ACCOUNT_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

--ALTER TABLE users ADD CONSTRAINT FK_USERS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES user_account (account_id);
--ALTER TABLE user_account ADD CONSTRAINT FK_USER_ACCOUNT_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

CREATE TABLE IF NOT EXISTS product (
  product_id UUID NOT NULL,
   title VARCHAR(120) NOT NULL,
   description VARCHAR(255),
   price NUMERIC(12,2) NOT NULL	,
   CONSTRAINT pk_product PRIMARY KEY (product_id),
   CONSTRAINT cc_positive_product_price CHECK (price >= 0)
);

CREATE TABLE IF NOT EXISTS purchase (
  purchase_id UUID NOT NULL,
   account_id UUID NOT NULL,
   created_at TIMESTAMP NOT NULL,
   CONSTRAINT pk_purchase PRIMARY KEY (purchase_id)
);

ALTER TABLE purchase ADD CONSTRAINT FK_PURCHASE_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES user_account (account_id);

CREATE TABLE IF NOT EXISTS purchase_detail (
   purchase_id UUID,
   product_id UUID,
   CONSTRAINT pk_purchasedetail PRIMARY KEY (purchase_id, product_id)
);

ALTER TABLE purchase_detail ADD CONSTRAINT FK_PURCHASEDETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE purchase_detail ADD CONSTRAINT FK_PURCHASEDETAIL_ON_PURCHASE FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id);