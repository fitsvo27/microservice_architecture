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
