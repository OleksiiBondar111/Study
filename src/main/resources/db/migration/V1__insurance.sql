CREATE TABLE public.insurance
(
    id               character varying(32) NOT NULL PRIMARY KEY,
    provider_name    character varying(32) NOT NULL,
    policy_number    character varying(32) NOT NULL,
    deleted          timestamp without time zone,
    created          timestamp without time zone,
    modified         timestamp without time zone,
    coverage_details text
);