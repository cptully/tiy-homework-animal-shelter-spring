--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animal; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE animal (
    id integer NOT NULL,
    color character varying(255),
    description character varying(255),
    name character varying(255),
    breed_id integer
);


ALTER TABLE animal OWNER TO chris;

--
-- Name: breed; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE breed (
    id integer NOT NULL,
    name character varying(255),
    type_id integer
);


ALTER TABLE breed OWNER TO chris;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO chris;

--
-- Name: note; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE note (
    id integer NOT NULL,
    content character varying(255),
    date bytea,
    animal_id integer
);


ALTER TABLE note OWNER TO chris;

--
-- Name: type; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE type (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE type OWNER TO chris;

--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animal (id, color, description, name, breed_id) FROM stdin;
0	black	skittish	Myst	0
\.


--
-- Data for Name: breed; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY breed (id, name, type_id) FROM stdin;
0	Calico	0
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY note (id, content, date, animal_id) FROM stdin;
0	Loves to chase bugs	\\x31322f32322f31393939	0
\.


--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY type (id, name) FROM stdin;
0	Cat\n
\.


--
-- Name: animal_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- Name: breed_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (id);


--
-- Name: note_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT note_pkey PRIMARY KEY (id);


--
-- Name: type_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY type
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- Name: fk30h5iofrmeyaw49dhel5cbra0; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT fk30h5iofrmeyaw49dhel5cbra0 FOREIGN KEY (type_id) REFERENCES type(id);


--
-- Name: fk5vuppijm6mptl6xm5g9jhegwh; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT fk5vuppijm6mptl6xm5g9jhegwh FOREIGN KEY (breed_id) REFERENCES breed(id);


--
-- Name: fkaro2ok22aqw0go52grtagv2fd; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT fkaro2ok22aqw0go52grtagv2fd FOREIGN KEY (animal_id) REFERENCES animal(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

