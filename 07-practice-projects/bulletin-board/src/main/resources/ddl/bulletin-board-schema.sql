-- bulletin-board DB 스키마 DDL
-- MySQL 기준

-- 1) 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS bulletin_board
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE bulletin_board;

-- 2) 회원 테이블 (members)
CREATE TABLE IF NOT EXISTS members (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(50)  NOT NULL,
    email      VARCHAR(100),
    role       VARCHAR(20)  NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uq_members_username UNIQUE (username),
    CONSTRAINT uq_members_email    UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) 게시글 테이블 (articles)
CREATE TABLE IF NOT EXISTS articles (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id  BIGINT       NOT NULL,
    title      VARCHAR(200) NOT NULL,
    content    TEXT         NOT NULL,
    view_count INT          NOT NULL DEFAULT 0,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_articles_member
        FOREIGN KEY (member_id) REFERENCES members(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 인덱스
CREATE INDEX idx_articles_member     ON articles(member_id);
CREATE INDEX idx_articles_created_at ON articles(created_at);

-- 4) 댓글 테이블 (comments)
CREATE TABLE IF NOT EXISTS comments (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    article_id BIGINT        NOT NULL,
    member_id  BIGINT        NOT NULL,
    content    VARCHAR(500)  NOT NULL,
    created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_comments_article
        FOREIGN KEY (article_id) REFERENCES articles(id),
    CONSTRAINT fk_comments_member
        FOREIGN KEY (member_id)  REFERENCES members(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 인덱스
CREATE INDEX idx_comments_article ON comments(article_id, created_at);
CREATE INDEX idx_comments_member  ON comments(member_id);

-- 참고:
-- 1) FK에는 ON DELETE CASCADE를 걸지 않았다.
--    → 삭제 로직은 애플리케이션(Service/Mapper)에서 명시적으로 처리.
-- 2) 필요 시 이메일 유니크 제약(CONSTRAINT uq_members_email)을 제거해도 된다.

ALTER TABLE comments
    DROP FOREIGN KEY fk_comments_article;

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_article
        FOREIGN KEY (article_id)
        REFERENCES articles(id)
        ON DELETE CASCADE;
