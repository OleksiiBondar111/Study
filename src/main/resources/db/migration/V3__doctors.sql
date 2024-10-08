CREATE TABLE public.doctors
(
    id             character varying(32) NOT NULL PRIMARY KEY,
    deleted        timestamp without time zone,
    created        timestamp without time zone,
    modified       timestamp without time zone,
    email          character varying(64) NOT NULL,
    gender         character varying(1) DEFAULT 'M'::character varying,
    first_name     character varying(32) NOT NULL,
    last_name      character varying(32) NOT NULL,
    specialty      character varying(32) NOT NULL,
    office_address text                  NOT NULL,
    phone          character varying(32),
    status         character varying(24),
    image_id       character varying(32),
    availability   text
);
