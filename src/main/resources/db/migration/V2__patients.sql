CREATE TABLE public.patients
(
    id           character varying(32)  NOT NULL PRIMARY KEY,
    deleted      timestamp without time zone,
    created      timestamp without time zone,
    modified     timestamp without time zone,
    email        character varying(64)  NOT NULL,
    gender       character varying(1) DEFAULT 'M'::character varying,
    first_name   character varying(32)  NOT NULL,
    last_name    character varying(32)  NOT NULL,
    address      character varying(100) NOT NULL,
    phone        character varying(32),
    status       character varying(24),
    image_id     character varying(32),
    insurance_id character varying(32)  REFERENCES public.insurance (id) ON DELETE SET NULL
);