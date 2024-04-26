INSERT INTO employee (
    employee_id, ssn, name, last_name, second_last_name, birthdate
) VALUES (
    1, '574-92-71XX', 'Eleonor Artemisa', 'Beck', 'Mayne', '2016-06-01'
);

INSERT INTO employee (
    employee_id, ssn, name, last_name, second_last_name, birthdate
) VALUES (
    2, '575-22-66XX', 'Angel Issac', 'Acevedo', 'Jonson', '2012-02-12'
);

SELECT setval('public.employee_employee_id_seq', 3, true);