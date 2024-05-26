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
   balance DOUBLE PRECISION NOT NULL,
   currency SMALLINT NOT NULL,
   is_active BOOLEAN NOT NULL,
   CONSTRAINT pk_user_account PRIMARY KEY (account_id)
);

ALTER TABLE user_account ADD CONSTRAINT FK_USER_ACCOUNT_ON_USER
FOREIGN KEY (user_id) REFERENCES users (user_id);