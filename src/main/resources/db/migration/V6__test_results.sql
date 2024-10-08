CREATE TABLE public.test_results
(
    id          character varying(32) NOT NULL PRIMARY KEY,
    record_id   character varying(32) REFERENCES public.medical_records (id) ON DELETE SET NULL,
    created     timestamp without time zone,
    modified    timestamp without time zone,
    test_date   timestamp without time zone,
    test_name   character varying(255),
    test_type   character varying(100),
    test_result text
);	