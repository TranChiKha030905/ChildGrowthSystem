CREATE TABLE blog_post_tags (
    post_id BIGINT NOT NULL,
    tag VARCHAR(255),
    PRIMARY KEY (post_id, tag),
 