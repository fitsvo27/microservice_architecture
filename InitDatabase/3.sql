CREATE TABLE IF NOT EXISTS images_storage
(
    user_id bigint NOT NULL,
    s3_link bit varying(255) NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (user_id),
    CONSTRAINT fk_user_avatar FOREIGN KEY (user_id)
            REFERENCES public.users (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);
