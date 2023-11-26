create table if not exists public.user_account
(
    id       serial
        primary key,
    username varchar(255)                                 not null,
    password varchar(255)                                 not null,
    name     varchar(255)                                 not null,
    surname  varchar(255)                                 not null,
    role     varchar default 'student'::character varying not null
);

alter table public.user_account
    owner to lallo;

create table if not exists public.student
(
    id              serial
        primary key,
    gpa             real,
    user_account_id integer
        unique
        references public.user_account
            on delete cascade
);

alter table public.student
    owner to lallo;

create table if not exists public.teacher
(
    id              serial
        primary key,
    taught_course   varchar(255) not null,
    user_account_id integer
        unique
        references public.user_account
            on delete cascade
);

alter table public.teacher
    owner to lallo;

create table if not exists public.course
(
    id   serial
        primary key,
    name varchar(255) not null,
    cfu  integer      not null
);

alter table public.course
    owner to lallo;

create table if not exists public.enrollment
(
    student_id integer not null
        references public.student
            on delete cascade,
    course_id  integer not null
        references public.course
            on delete cascade,
    grade      integer,
    primary key (student_id, course_id)
);

alter table public.enrollment
    owner to lallo;


-- Insert test values into public.user_account table
INSERT INTO public.user_account (username, password, name, surname, role)
VALUES
    ('user1', 'password1', 'John', 'Doe', 'student'),
    ('jane_smith', 'pass456', 'Jane', 'Smith', 'teacher'),
    ('bob_johnson', 'secret789', 'Bob', 'Johnson', 'student');

-- Insert test values into public.student table
INSERT INTO public.student (gpa, user_account_id)
VALUES
    (3.8, 1),
    (3.5, 2),
    (4.0, 3);

-- Insert test values into public.teacher table
INSERT INTO public.teacher (taught_course, user_account_id)
VALUES
    ('Mathematics', 2);

-- Insert test values into public.course table
INSERT INTO public.course (name, cfu)
VALUES
    ('Math 101', 3),
    ('History 202', 4);

-- Insert test values into public.enrollment table
INSERT INTO public.enrollment (student_id, course_id, grade)
VALUES
    (1, 1, 90),
    (2, 2, 85),
    (3, 1, 95); -- Corrected to reference an existing student_id
