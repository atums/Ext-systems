DROP TABLE IF EXISTS ro_marriage_certificate;
DROP TABLE IF EXISTS ro_birth_certificate;
DROP TABLE IF EXISTS ro_passport;
DROP TABLE IF EXISTS ro_person;

CREATE TABLE ro_person
(
    person_id  int GENERATED ALWAYS AS IDENTITY NOT NULL,
    sex        smallint                         NOT NULL,
    first_name varchar(100)                     NOT NULL,
    last_name  varchar(100)                     NOT NULL,
    patronymic varchar(100),
    date_birth date                             NOT NULL,
    CONSTRAINT pk_ro_person_person_id PRIMARY KEY (person_id)
);

CREATE TABLE ro_passport
(
    passport_id      int GENERATED ALWAYS AS IDENTITY NOT NULL,
    person_id        int                              NOT NULL,
    serial           varchar(10)                      NOT NULL,
    number           varchar(10)                      NOT NULL,
    date_issue       date                             NOT NULL,
    issue_department varchar(200),
    CONSTRAINT pk_ro_passport_passport_id PRIMARY KEY (passport_id),
    CONSTRAINT fk_ro_passport_person_id FOREIGN KEY (person_id)
        REFERENCES ro_person (person_id) ON DELETE RESTRICT
);

CREATE TABLE ro_birth_certificate
(
    birth_certificate_id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    number_certificate   varchar(20)                      NOT NULL,
    date_issue           date                             NOT NULL,
    person_id            int                              NOT NULL,
    father_id            int,
    mother_id            int,
    CONSTRAINT pk_ro_birth_certificate_birth_certificate_id PRIMARY KEY (birth_certificate_id),
    CONSTRAINT fk_ro_birth_certificate_person_id FOREIGN KEY (person_id)
        REFERENCES ro_person (person_id) ON DELETE RESTRICT,
    CONSTRAINT fk_ro_birth_certificate_father_id FOREIGN KEY (father_id)
        REFERENCES ro_person (person_id) ON DELETE RESTRICT,
    CONSTRAINT fk_ro_birth_certificate_mother_id FOREIGN KEY (mother_id)
        REFERENCES ro_person (person_id) ON DELETE RESTRICT
);

CREATE TABLE ro_marriage_certificate
(
    marriage_certificate_id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    number_certificate      varchar(20)                      NOT NULL,
    date_issue              date                             NOT NULL,
    husband_id              int                              NOT NULL,
    wife_id                 int                              NOT NULL,
    active                  bool DEFAULT false,
    end_date                date,
    CONSTRAINT pk_ro_marriage_certificate_marriage_certificate_id PRIMARY KEY (marriage_certificate_id),
    CONSTRAINT fk_ro_marriage_certificate_husband_id FOREIGN KEY (husband_id)
        REFERENCES ro_person (person_id) ON DELETE RESTRICT,
    CONSTRAINT fk_ro_marriage_certificate_wife_id FOREIGN KEY (wife_id)
        REFERENCES ro_person (person_id) ON DELETE RESTRICT
);

INSERT INTO ro_person(sex, first_name, last_name, patronymic, date_birth)
VALUES (1, 'Marina', 'Tums', ' ', '1976-04-24'),
       (2, 'Alex', 'Tums', ' ', '1978-01-27'),
       (1, 'Nika', 'Tums', ' ', '2020-03-05');

INSERT INTO ro_passport(person_id, serial, number, date_issue, issue_department)
VALUES (1, '4004', '123456', '2003-06-07', 'Department pass');

INSERT INTO ro_birth_certificate(number_certificate, date_issue, person_id, father_id, mother_id)
VALUES ('123 Birth', '2020-08-10', 3, 2, 1);


