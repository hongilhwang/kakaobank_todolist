CREATE DATABASE IF NOT EXISTS todotask;

ALTER DATABASE todotask
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

DROP USER IF EXISTS 'todo'@'localhost';
CREATE USER todo@localhost IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON todotask.* TO todo@localhost IDENTIFIED BY 'todo';
