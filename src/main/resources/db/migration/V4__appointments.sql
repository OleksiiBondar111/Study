CREATE TABLE public.appointments
(
    id               character varying(32) NOT NULL PRIMARY KEY,
    deleted          timestamp without time zone,
    created          timestamp without time zone,
    modified         timestamp without time zone,
    patient_id       character varying(32) REFERENCES public.patients (id) ON DELETE SET NULL,
    doctor_id        character varying(32) REFERENCES public.doctors (id) ON DELETE SET NULL,
    appointment_date timestamp without time zone,
    status           character varying(32) DEFAULT 'pending'::character varying
);
