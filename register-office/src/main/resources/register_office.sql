DROP TABLE IF EXISTS person;

CREATE TABLE person (
    person_id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    first_name varchar(100),
    last_name varchar(100),
    CONSTRAINT pk_person_person_id PRIMARY KEY (person_id)
);