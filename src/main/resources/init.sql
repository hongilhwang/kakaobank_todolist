CREATE DATABASE IF NOT EXISTS todotask;

CREATE DATABASE IF NOT EXISTS operation;

ALTER DATABASE todotask
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;


ALTER DATABASE operation
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

-- ------------
-- 계정 생성
-- -------------
DROP USER IF EXISTS 'todo'@'%';
CREATE USER 'todo'@'%' IDENTIFIED BY '1234';

-- ---------------
-- 데이터베이스 권한 부여
-- -----------------
GRANT ALL PRIVILEGES ON todotask.* TO todo@'%';
GRANT ALL PRIVILEGES ON operation.* TO todo@'%';

