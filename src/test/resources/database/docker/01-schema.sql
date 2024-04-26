CREATE TABLE employee (
    employee_id serial,
    ssn VARCHAR(12),
    name VARCHAR(30) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    second_last_name VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL
);

ALTER TABLE employee ADD CONSTRAINT  employee_pk PRIMARY KEY (employee_id);

