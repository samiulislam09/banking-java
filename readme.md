> Project Introduction

I have used MySQL for database. In the beginning of the program i have added 2 dummy data to database.

> database schema :
### Creating database:
```
CREATE DATABASE banking;
```
### Creating accounts table:
```
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_holder_name VARCHAR(100) NOT NULL,
    balance INT NOT NULL,
    accountNumber VARCHAR(20) UNIQUE NOT NULL
);
```
### Creating Transactions table:
```
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (account_id) REFERENCES banking.accounts(account_id),
    transaction_type VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```