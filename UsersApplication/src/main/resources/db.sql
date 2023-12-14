create extension if not exists pg_stat_statements;
CREATE TABLE IF NOT EXISTS "users"
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    login character varying(255),
    lastname character varying(255),
    firstname character varying(255),
    middlename character varying(255),
    gender character(1),
    date_of_birth timestamp,
    avatar_link character varying(255),
    email character varying(255),
    phone_number character varying(255),
    deleted boolean,
    CONSTRAINT user_id_pk PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS friends
(
    user_id bigint NOT NULL,
    user_friend_id bigint NOT NULL,
    CONSTRAINT user_friends_pk PRIMARY KEY (user_id,user_friend_id)
);
CREATE TABLE IF NOT EXISTS images_storage
(
    user_id bigint NOT NULL,
    s3_link bit varying(255) NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (user_id)
);
