--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

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
-- Name: course; Type: TABLE; Schema: public; Owner: lallo
--

CREATE TABLE public.course (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    cfu integer NOT NULL
);


ALTER TABLE public.course OWNER TO lallo;

--
-- Name: course_id_seq; Type: SEQUENCE; Schema: public; Owner: lallo
--

CREATE SEQUENCE public.course_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_id_seq OWNER TO lallo;

--
-- Name: course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lallo
--

ALTER SEQUENCE public.course_id_seq OWNED BY public.course.id;


--
-- Name: enrollment; Type: TABLE; Schema: public; Owner: lallo
--

CREATE TABLE public.enrollment (
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    grade integer
);


ALTER TABLE public.enrollment OWNER TO lallo;

--
-- Name: student; Type: TABLE; Schema: public; Owner: lallo
--

CREATE TABLE public.student (
    id integer NOT NULL,
    gpa real,
    user_account_id integer
);


ALTER TABLE public.student OWNER TO lallo;

--
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: lallo
--

CREATE SEQUENCE public.student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_id_seq OWNER TO lallo;

--
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lallo
--

ALTER SEQUENCE public.student_id_seq OWNED BY public.student.id;


--
-- Name: teacher; Type: TABLE; Schema: public; Owner: lallo
--

CREATE TABLE public.teacher (
    id integer NOT NULL,
    taught_course character varying(255) NOT NULL,
    user_account_id integer
);


ALTER TABLE public.teacher OWNER TO lallo;

--
-- Name: teacher_id_seq; Type: SEQUENCE; Schema: public; Owner: lallo
--

CREATE SEQUENCE public.teacher_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.teacher_id_seq OWNER TO lallo;

--
-- Name: teacher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lallo
--

ALTER SEQUENCE public.teacher_id_seq OWNED BY public.teacher.id;


--
-- Name: user_account; Type: TABLE; Schema: public; Owner: lallo
--

CREATE TABLE public.user_account (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    role character varying DEFAULT 'student'::character varying NOT NULL
);


ALTER TABLE public.user_account OWNER TO lallo;

--
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: lallo
--

CREATE SEQUENCE public.user_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_account_id_seq OWNER TO lallo;

--
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lallo
--

ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;


--
-- Name: course id; Type: DEFAULT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.course ALTER COLUMN id SET DEFAULT nextval('public.course_id_seq'::regclass);


--
-- Name: student id; Type: DEFAULT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.student ALTER COLUMN id SET DEFAULT nextval('public.student_id_seq'::regclass);


--
-- Name: teacher id; Type: DEFAULT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.teacher ALTER COLUMN id SET DEFAULT nextval('public.teacher_id_seq'::regclass);


--
-- Name: user_account id; Type: DEFAULT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);


--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: lallo
--

COPY public.course (id, name, cfu) FROM stdin;
1	Math 101	3
2	History 202	4
3	Computer Science 301	5
\.


--
-- Data for Name: enrollment; Type: TABLE DATA; Schema: public; Owner: lallo
--

COPY public.enrollment (student_id, course_id, grade) FROM stdin;
1	1	90
2	2	85
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: lallo
--

COPY public.student (id, gpa, user_account_id) FROM stdin;
1	3.8	1
2	3.5	2
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: lallo
--

COPY public.teacher (id, taught_course, user_account_id) FROM stdin;
1	Math 101	3
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: lallo
--

COPY public.user_account (id, username, password, name, surname, role) FROM stdin;
1	user1	password1	John	Doe	student
2	user2	password2	Jane	Smith	student
3	user3	password3	Bob	Johnson	teacher
\.


--
-- Name: course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: lallo
--

SELECT pg_catalog.setval('public.course_id_seq', 3, true);


--
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: lallo
--

SELECT pg_catalog.setval('public.student_id_seq', 2, true);


--
-- Name: teacher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: lallo
--

SELECT pg_catalog.setval('public.teacher_id_seq', 1, true);


--
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: lallo
--

SELECT pg_catalog.setval('public.user_account_id_seq', 3, true);


--
-- Name: course course_pkey; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: enrollment enrollment_pkey; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_pkey PRIMARY KEY (student_id, course_id);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: student student_user_account_id_key; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_user_account_id_key UNIQUE (user_account_id);


--
-- Name: teacher teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- Name: teacher teacher_user_account_id_key; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT teacher_user_account_id_key UNIQUE (user_account_id);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: enrollment enrollment_course_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id) ON DELETE CASCADE;


--
-- Name: enrollment enrollment_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.student(id) ON DELETE CASCADE;


--
-- Name: student student_user_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_user_account_id_fkey FOREIGN KEY (user_account_id) REFERENCES public.user_account(id) ON DELETE CASCADE;


--
-- Name: teacher teacher_user_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lallo
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT teacher_user_account_id_fkey FOREIGN KEY (user_account_id) REFERENCES public.user_account(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

