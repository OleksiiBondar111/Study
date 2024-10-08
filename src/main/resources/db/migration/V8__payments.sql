CREATE TABLE public.payments
(
    id             character varying(32) NOT NULL PRIMARY KEY,
    invoice_id     character varying(32) REFERENCES public.invoices (id) ON DELETE SET NULL,
    created        timestamp without time zone,
    modified       timestamp without time zone,
    payment_method character varying(50),
    issue_date     timestamp without time zone,
    payment_amount decimal(10, 2)
);	