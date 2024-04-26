package org.directory.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.directory.entity.Employee;
import org.directory.exception.NotFountException;
import org.directory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/employees")
@Tag(name = "Employees", description = "Allows to update employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@RequestBody final Employee employee) {
        var savedEmployee = service.add(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable(value = "id") final long idEmployee)
            throws NotFountException {
        var employee = service.findById(idEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = service.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable(value = "id") final long id)
            throws NotFountException {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") final long id,
               @RequestBody final Employee employee) throws NotFountException {
        var updatedEmployee = service.update(id, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<String> notFoundExceptionHandler(
            final NotFountException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
    }
}

