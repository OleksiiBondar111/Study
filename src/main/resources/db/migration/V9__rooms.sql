CREATE TABLE public.rooms
(
    id           character varying(32) NOT NULL PRIMARY KEY,
    deleted      timestamp without time zone,
    created      timestamp without time zone,
    modified     timestamp without time zone,
    room_number  character varying(32),
    room_type    character varying(32),
    availability boolean
);