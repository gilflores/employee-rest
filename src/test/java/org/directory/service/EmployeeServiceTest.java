package org.directory.service;

import org.directory.entity.Employee;
import org.directory.exception.NotFountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @Test
    void testAddEmployee() {
        // GIVEN
        Employee newEmployee = new Employee();
        newEmployee.setSsn("575-22-66ZZ");
        newEmployee.setName("Ismael");
        newEmployee.setLastName("Rodriguez");
        newEmployee.setSecondLastName("Vasquez");
        newEmployee.setBirthdate(LocalDate.of(2019,1,10));

        // WHEN
        Employee savedEmployee = service.add(newEmployee);

        // THEN
        assertEquals(newEmployee, savedEmployee);
    }

    @Test
    void testFindByIdExistingEmployee() throws NotFountException {
        // GIVEN
        Employee newEmployee = new Employee();
        newEmployee.setSsn("478-42-34AA");
        newEmployee.setName("Ximena");
        newEmployee.setLastName("Wong");
        newEmployee.setSecondLastName("Shin");
        newEmployee.setBirthdate(LocalDate.of(2019,1,10));

        Employee savedEmployee = service.add(newEmployee);

        // WHEN
        Employee employee = service.findById(savedEmployee.getId());

        // THEN
        assertEquals(savedEmployee, employee);
    }

    @Test
    void testFindByIdNotExistingEmployee() {
        // GIVEN
        long notExistingId = Long.MAX_VALUE;

        // WHEN
        try {
            service.findById(notExistingId);
            fail();
        } catch (NotFountException pnee) {
            // THEN
            assertTrue(true);
        }
    }

    @Test
    void testDeleteByIdExistingEmployee() throws NotFountException {
        // GIVEN
        Employee newEmployee = new Employee();
        newEmployee.setSsn("433-39-50BB");
        newEmployee.setName("Katerin");
        newEmployee.setLastName("Billy");
        newEmployee.setSecondLastName("Willis");
        newEmployee.setBirthdate(LocalDate.of(2019,1,10));

        Employee savedEmployee = service.add(newEmployee);

        // WHEN
        service.deleteById(savedEmployee.getId());

        // THEN
        try {
            service.findById(savedEmployee.getId());
            fail();
        } catch (NotFountException pnee) {
            // THEN
            assertTrue(true);
        }
    }

    @Test
    void testDeleteByIdNotExistingEmployee() {
        // GIVEN
        long notExistingId = Long.MAX_VALUE;

        // WHEN
        try {
            service.deleteById(notExistingId);
            fail();
        } catch (NotFountException pnee) {
            // THEN
            assertTrue(true);
        }
    }

    @Test
    void testUpdateExistingEmployee() throws NotFountException {
        // GIVEN
        Employee newEmployee = new Employee();
        newEmployee.setSsn("304-96-30EE");
        newEmployee.setName("Antony");
        newEmployee.setLastName("Boying");
        newEmployee.setSecondLastName("Liz");
        newEmployee.setBirthdate(LocalDate.of(2019,1,10));

        Employee savedEmployee = service.add(newEmployee);
        savedEmployee.setSecondLastName("Holchin");

        // WHEN
        Employee updatedEmployee = service.update(
                savedEmployee.getId(), savedEmployee);

        // THEN
        assertEquals(savedEmployee, updatedEmployee);
    }

    @Test
    void testUpdateNotExistingEmployee() {
        // GIVEN
        Employee invalidEmployee = new Employee();
        invalidEmployee.setId(Long.MAX_VALUE);
        invalidEmployee.setSsn("136-86-20DD");
        invalidEmployee.setName("Alvaro");
        invalidEmployee.setLastName("Esparza");
        invalidEmployee.setSecondLastName("Munungi");
        invalidEmployee.setBirthdate(LocalDate.now());

        // WHEN
        try {
            Employee updatedEmployee = service.update(
                    invalidEmployee.getId(), invalidEmployee);
            fail();
        } catch (NotFountException pnee) {
            // THEN
            assertTrue(true);
        }
    }
}
