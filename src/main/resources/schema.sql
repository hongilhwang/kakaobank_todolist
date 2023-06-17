use todotask;
CREATE TABLE IF NOT EXISTS task
(
    id          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255)                   DEFAULT NULL,
    description BLOB                           DEFAULT NULL,
    priority    ENUM ('low', 'normal', 'high') DEFAULT NULL,
    status      BIT(1)           NOT NULL,
    reporter    VARCHAR(255)                   DEFAULT NULL COMMENT '작성자 / 이슈 제기자',
    due_date    DATETIME(6)                    DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS comment
(
    id      INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    text    VARCHAR(255)     DEFAULT NULL,
    task_id INT(11) UNSIGNED DEFAULT NULL,
    date    DATETIME(6)      DEFAULT NULL,
    FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE
) ENGINE = InnoDB;


-- User Table 추가
CREATE TABLE IF NOT EXISTS user
(
    id   INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 번호',
    name VARCHAR(255) NOT NULL UNIQUE COMMENT '사용자 이름',
    pw   VARCHAR(255) NOT NULL COMMENT '사용자 패스워드',
    admin BIT(1)      NOT NULL DEFAULT 0 COMMENT '관리자 여부 (1: 관리자, 0: 유저)',
    del   BIT(1)      NOT NULL DEFAULT 0 COMMENT '삭제 여부 (1: 삭제, 0: 미삭제)'
) comment '사용자 테이블'
ENGINE = InnoDB;