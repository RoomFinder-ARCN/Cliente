--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE root;
ALTER ROLE root WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:n7wBvBXcD1P0M/gU+w/UbQ==$niuMs7Yb00MyQGJaQYAcNVo2gRufiL6rHCOLIBTW1lE=:5Xumpunpw9v5sbu1JWpDIR3mNbk0wclB61L6yA6Nn+A=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2 (Debian 16.2-1.pgdg120+2)

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
-- PostgreSQL database dump complete
--

--
-- Database "clientedb" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2 (Debian 16.2-1.pgdg120+2)

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
-- Name: clientedb; Type: DATABASE; Schema: -; Owner: root
--

CREATE DATABASE clientedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE clientedb OWNER TO root;

\connect clientedb

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clientes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.clientes (
    correo character varying NOT NULL,
    nombre character varying NOT NULL,
    tipo_documento character varying NOT NULL,
    numero_documento character varying NOT NULL
);


ALTER TABLE public.clientes OWNER TO root;

--
-- Name: cuentas_bancarias; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cuentas_bancarias (
    numero_cuenta character varying NOT NULL,
    cantidad_credito numeric NOT NULL,
    cliente character varying NOT NULL
);


ALTER TABLE public.cuentas_bancarias OWNER TO root;

--
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.clientes (correo, nombre, tipo_documento, numero_documento) FROM stdin;
eduard.arias@gmail.com	Eduard Arias	CC	102555
\.


--
-- Data for Name: cuentas_bancarias; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cuentas_bancarias (numero_cuenta, cantidad_credito, cliente) FROM stdin;
98722	30000	eduard.arias@gmail.com
\.


--
-- Name: clientes clientes_pk; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pk PRIMARY KEY (correo);


--
-- Name: cuentas_bancarias cuentas_bancarias_pk; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cuentas_bancarias
    ADD CONSTRAINT cuentas_bancarias_pk PRIMARY KEY (numero_cuenta);


--
-- Name: cuentas_bancarias cuentas_bancarias_clientes_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cuentas_bancarias
    ADD CONSTRAINT cuentas_bancarias_clientes_fk FOREIGN KEY (cliente) REFERENCES public.clientes(correo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2 (Debian 16.2-1.pgdg120+2)

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
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

