CREATE SEQUENCE IF NOT EXISTS public.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.user_product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    username text NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.user_product
(
    id bigint NOT NULL DEFAULT nextval('user_product_id_seq'::regclass),
    user_id bigint NOT NULL,
    balance real,
    type text,
    acc_num text,
    CONSTRAINT user_product_pkey PRIMARY KEY (id)
);