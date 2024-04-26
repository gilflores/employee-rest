package org.directory.repository;

import org.directory.entity.Employee;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryTest.class);

    @Autowired
    EmployeeRepository repository;

    @Test
    void testFindById() {
        Optional<Employee> result = repository.findById(1L);
        assertTrue(result.isPresent(), "The employee must exists in the database");
        Employee employee = result.get();
        assertEquals("574-92-71XX", employee.getSsn());
        assertEquals("Eleonor Artemisa", employee.getName());
        assertEquals("Beck", employee.getLastName());
        assertEquals("Mayne", employee.getSecondLastName());
        assertEquals(LocalDate.of(2016, 6, 1),  employee.getBirthdate() );
    }

    @Test
    void testAddNewEmployee() {
        Employee employee = createEmployee();
        Employee result = repository.save(employee);
        LOGGER.info("Generated ID: {}", employee.getId());
        assertTrue(employee.getId() > 2,
                "The id must be greater than the number of pre-existing records");
        validateEmployee(employee, result);
    }

    @Test
    void testUpdate() {
        final long idToModify = 2;
        Employee employee = createEmployee();
        employee.setId(idToModify);
        Employee result = repository.save(employee);
        assertEquals(idToModify, employee.getId(),
                "El id de la Employee debe ser 2");
        validateEmployee(employee, result);
    }

    @Test
    void testAddWithError() {
        Employee employee = createEmployee();
        employee.setSsn("505-20-92XX.12");
        DataIntegrityViolationException ex = assertThrows(
                DataIntegrityViolationException.class, () -> repository.save(employee));
        assertNotNull(ex);
    }

    @Test
    void testFindAll() {
        List<Employee> employees = repository.findAll();
        assertFalse(employees.isEmpty(), "The list must have records");
        for (Employee employee : employees) {
            assertFalse(employee.getSsn().isBlank());
            assertFalse(employee.getName().isBlank());
            LOGGER.info("{}", employee);
        }
    }


    private void validateEmployee(final Employee expected, final Employee result) {
        assertEquals(expected.getSsn(), result.getSsn());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getSecondLastName(), result.getSecondLastName());
        assertEquals(expected.getBirthdate(),  result.getBirthdate());
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setSsn("505-20-92XX");
        employee.setLastName("Sims");
        employee.setSecondLastName("Vega");
        employee.setName("Aaron");
        employee.setBirthdate(LocalDate.of(2010, 4,15));
        return employee;
    }
}
