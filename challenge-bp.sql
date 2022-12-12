--
-- PostgreSQL database dump
--

-- Dumped from database version 10.15
-- Dumped by pg_dump version 10.15

-- Started on 2022-12-12 05:22:41 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "challenge-bp";
--
-- TOC entry 2938 (class 1262 OID 158914)
-- Name: challenge-bp; Type: DATABASE; Schema: -; Owner: test
--

CREATE DATABASE "challenge-bp" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_PE.UTF-8' LC_CTYPE = 'es_PE.UTF-8';


ALTER DATABASE "challenge-bp" OWNER TO test;

\connect -reuse-previous=on "dbname='challenge-bp'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 158915)
-- Name: banco; Type: SCHEMA; Schema: -; Owner: test
--

CREATE SCHEMA banco;


ALTER SCHEMA banco OWNER TO test;

--
-- TOC entry 1 (class 3079 OID 13003)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 202 (class 1259 OID 159138)
-- Name: account; Type: TABLE; Schema: banco; Owner: test
--

CREATE TABLE banco.account (
    account_id bigint NOT NULL,
    account_number character varying(255) NOT NULL,
    account_type character varying(255),
    inicial_balance numeric(19,2),
    status boolean NOT NULL,
    client_id bigint
);


ALTER TABLE banco.account OWNER TO test;

--
-- TOC entry 203 (class 1259 OID 159146)
-- Name: client; Type: TABLE; Schema: banco; Owner: test
--

CREATE TABLE banco.client (
    client_id bigint NOT NULL,
    address character varying(255),
    age integer,
    gender character varying(255),
    identification character varying(255),
    name character varying(255),
    phone character varying(255),
    password character varying(255),
    status boolean NOT NULL
);


ALTER TABLE banco.client OWNER TO test;

--
-- TOC entry 204 (class 1259 OID 159154)
-- Name: id_gen; Type: TABLE; Schema: banco; Owner: test
--

CREATE TABLE banco.id_gen (
    name_pk character varying(255) NOT NULL,
    value_pk integer
);


ALTER TABLE banco.id_gen OWNER TO test;

--
-- TOC entry 205 (class 1259 OID 159159)
-- Name: movement; Type: TABLE; Schema: banco; Owner: test
--

CREATE TABLE banco.movement (
    movement_id character varying(255) NOT NULL,
    balance numeric(19,2),
    date_movement date,
    description character varying(255),
    movement_type character varying(255),
    value numeric(19,2),
    account_id bigint
);


ALTER TABLE banco.movement OWNER TO test;

--
-- TOC entry 198 (class 1259 OID 158918)
-- Name: account; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.account (
    account_id bigint NOT NULL,
    account_number character varying(255),
    account_type character varying(255),
    inicial_balance numeric(38,2),
    status boolean NOT NULL
);


ALTER TABLE public.account OWNER TO test;

--
-- TOC entry 197 (class 1259 OID 158916)
-- Name: account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.account_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_account_id_seq OWNER TO test;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 197
-- Name: account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.account_account_id_seq OWNED BY public.account.account_id;


--
-- TOC entry 199 (class 1259 OID 158927)
-- Name: client; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    address character varying(255),
    age integer,
    gender character varying(255),
    identification character varying(255),
    name character varying(255),
    phone character varying(255),
    password character varying(255),
    status boolean NOT NULL
);


ALTER TABLE public.client OWNER TO test;

--
-- TOC entry 201 (class 1259 OID 158937)
-- Name: movement; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.movement (
    movement_id bigint NOT NULL,
    balance numeric(38,2),
    date timestamp(6) without time zone,
    movement_type character varying(255),
    value numeric(38,2)
);


ALTER TABLE public.movement OWNER TO test;

--
-- TOC entry 200 (class 1259 OID 158935)
-- Name: movement_movement_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.movement_movement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movement_movement_id_seq OWNER TO test;

--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 200
-- Name: movement_movement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.movement_movement_id_seq OWNED BY public.movement.movement_id;


--
-- TOC entry 2781 (class 2604 OID 158921)
-- Name: account account_id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.account ALTER COLUMN account_id SET DEFAULT nextval('public.account_account_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 158940)
-- Name: movement movement_id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.movement ALTER COLUMN movement_id SET DEFAULT nextval('public.movement_movement_id_seq'::regclass);


--
-- TOC entry 2929 (class 0 OID 159138)
-- Dependencies: 202
-- Data for Name: account; Type: TABLE DATA; Schema: banco; Owner: test
--

INSERT INTO banco.account (account_id, account_number, account_type, inicial_balance, status, client_id) VALUES (3, '77777', 'CORRIENTE', 10.00, true, 2);


--
-- TOC entry 2930 (class 0 OID 159146)
-- Dependencies: 203
-- Data for Name: client; Type: TABLE DATA; Schema: banco; Owner: test
--

INSERT INTO banco.client (client_id, address, age, gender, identification, name, phone, password, status) VALUES (2, 'quis et do', 14330414, 'MASCULINO', '0123456789', 'David Mogrovejo', '0987654321', 'test', true);
INSERT INTO banco.client (client_id, address, age, gender, identification, name, phone, password, status) VALUES (4, 'quis et do', 123, 'MASCULINO', '0123456789', 'asd', '0987654321', 'test', true);


--
-- TOC entry 2931 (class 0 OID 159154)
-- Dependencies: 204
-- Data for Name: id_gen; Type: TABLE DATA; Schema: banco; Owner: test
--

INSERT INTO banco.id_gen (name_pk, value_pk) VALUES ('CLIENT_ID', 5);
INSERT INTO banco.id_gen (name_pk, value_pk) VALUES ('ACCOUNT_ID', 3);


--
-- TOC entry 2932 (class 0 OID 159159)
-- Dependencies: 205
-- Data for Name: movement; Type: TABLE DATA; Schema: banco; Owner: test
--

INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504c384c80000', 10.00, '2022-12-12', 'Ingreso Inicial por Apertura de Cuenta', 'CREDITO', 10.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504d431440001', 0.00, '2022-12-12', 'Retirar dinero', 'DEBITO', -10.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504d4e96e0002', 5000.00, '2022-12-12', 'Retirar dinero', 'CREDITO', 5000.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504d546060003', 4500.00, '2022-12-12', 'Retirar dinero', 'DEBITO', -500.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504d56b930004', 4100.00, '2022-12-12', 'Retirar dinero', 'DEBITO', -400.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504d5899b0005', 4000.00, '2022-12-12', 'Retirar dinero', 'DEBITO', -100.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504d59be90006', 3900.00, '2022-12-12', 'Retirar dinero', 'DEBITO', -100.00, 3);
INSERT INTO banco.movement (movement_id, balance, date_movement, description, movement_type, value, account_id) VALUES ('ff8081818504a215018504da957a0007', 4000.00, '2022-12-12', 'Retirar dinero', 'CREDITO', 100.00, 3);


--
-- TOC entry 2925 (class 0 OID 158918)
-- Dependencies: 198
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: test
--



--
-- TOC entry 2926 (class 0 OID 158927)
-- Dependencies: 199
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: test
--



--
-- TOC entry 2928 (class 0 OID 158937)
-- Dependencies: 201
-- Data for Name: movement; Type: TABLE DATA; Schema: public; Owner: test
--



--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 197
-- Name: account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.account_account_id_seq', 1, false);


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 200
-- Name: movement_movement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.movement_movement_id_seq', 1, false);


--
-- TOC entry 2790 (class 2606 OID 159145)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- TOC entry 2794 (class 2606 OID 159153)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (client_id);


--
-- TOC entry 2798 (class 2606 OID 159158)
-- Name: id_gen id_gen_pkey; Type: CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.id_gen
    ADD CONSTRAINT id_gen_pkey PRIMARY KEY (name_pk);


--
-- TOC entry 2800 (class 2606 OID 159166)
-- Name: movement movement_pkey; Type: CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.movement
    ADD CONSTRAINT movement_pkey PRIMARY KEY (movement_id);


--
-- TOC entry 2792 (class 2606 OID 159168)
-- Name: account uk_66gkcp94endmotfwb8r4ocxm9; Type: CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.account
    ADD CONSTRAINT uk_66gkcp94endmotfwb8r4ocxm9 UNIQUE (account_number);


--
-- TOC entry 2796 (class 2606 OID 159170)
-- Name: client uk_dn5jasds5r1j3ewo5k3nhwkkq; Type: CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.client
    ADD CONSTRAINT uk_dn5jasds5r1j3ewo5k3nhwkkq UNIQUE (name);


--
-- TOC entry 2784 (class 2606 OID 158926)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- TOC entry 2786 (class 2606 OID 158934)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 2788 (class 2606 OID 158942)
-- Name: movement movement_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_pkey PRIMARY KEY (movement_id);


--
-- TOC entry 2801 (class 2606 OID 159171)
-- Name: account fkkm8yb63h4ownvnlrbwnadntyn; Type: FK CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.account
    ADD CONSTRAINT fkkm8yb63h4ownvnlrbwnadntyn FOREIGN KEY (client_id) REFERENCES banco.client(client_id);


--
-- TOC entry 2802 (class 2606 OID 159176)
-- Name: movement fkoemeananv9w9qnbcoccbl70a0; Type: FK CONSTRAINT; Schema: banco; Owner: test
--

ALTER TABLE ONLY banco.movement
    ADD CONSTRAINT fkoemeananv9w9qnbcoccbl70a0 FOREIGN KEY (account_id) REFERENCES banco.account(account_id);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2022-12-12 05:22:41 -05

--
-- PostgreSQL database dump complete
--

