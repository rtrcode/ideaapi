CREATE TABLE idea_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created timestamp
);

CREATE TABLE idea (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(400) NOT NULL,
    content VARCHAR(2000) NULL,
    image_url VARCHAR(400) NULL,
    created timestamp
);

ALTER TABLE idea
    ADD CONSTRAINT idea_user_id
    FOREIGN KEY (user_id) REFERENCES idea_user(id);

CREATE TABLE idea_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idea_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

ALTER TABLE idea_comment
    ADD CONSTRAINT comment_idea_id
    FOREIGN KEY (idea_id) REFERENCES idea(id);

ALTER TABLE idea_comment
    ADD CONSTRAINT comment_user_id
    FOREIGN KEY (user_id) REFERENCES idea_user(id);

CREATE TABLE idea_like (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idea_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created timestamp
);

ALTER TABLE idea_like
    ADD CONSTRAINT like_idea_id
    FOREIGN KEY (idea_id) REFERENCES idea(id);

ALTER TABLE idea_like
    ADD CONSTRAINT like_user_id
    FOREIGN KEY (user_id) REFERENCES idea_user(id);

