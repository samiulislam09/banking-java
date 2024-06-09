# Project Introduction

I have used MySQL for database. In the beginning of the program i have added 2 dummy data to database.

To run the program you need to create the database first. The database name should be ***banking***. If you give the database name wrong it won't run perfectly. After creating the database you need to run ***USE banking*** command in mysql shell. For creating the table run the schema i have defined below. To run the program press the start button in the top of your text editor.

After running the app there will display some commands, and you need to press the commands from your numpad.
# database schema
### Creating database:
```
CREATE DATABASE banking;
```
### Using banking DB
```
USE banking;
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
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES banking.accounts(account_id),
    transaction_type VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```