CREATE TABLE IF NOT EXISTS images_storage
(
    user_id bigint NOT NULL,
    s3_link bit varying(255) NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (user_id)
);