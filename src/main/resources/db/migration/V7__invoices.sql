CREATE TABLE public.invoices
(
    id             character varying(32) NOT NULL PRIMARY KEY,
    deleted        timestamp without time zone,
    created        timestamp without time zone,
    modified       timestamp without time zone,
    patient_id     character varying(32) REFERENCES public.patients (id) ON DELETE SET NULL,
    appointment_id character varying(32) REFERENCES public.appointments (id) ON DELETE SET NULL,
    total_amount   decimal(10, 2),
    status         character varying(1),
    issue_date     timestamp without time zone,
    due_date       timestamp without time zone
);