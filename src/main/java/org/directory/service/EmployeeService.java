package org.directory.service;

import org.directory.entity.Employee;
import org.directory.exception.NotFountException;
import org.directory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee findById(final long EmployeeId) throws NotFountException {
        /*
        // Using the classic approach example
        Optional<Employee> resultFinByID = repository.findById(EmployeeId);

        if (resultFinByID.isPresent()) {
            return resultFinByID.get();
        } else {
            throw new NotFountException();
        }
        */

        Optional<Employee> resultFinByID = repository.findById(EmployeeId);
        resultFinByID.orElseThrow(NotFountException::new);
        return resultFinByID.get();
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee add(final Employee newEmployee) {
        return repository.save(newEmployee);
    }

    public void deleteById(final long EmployeeId) throws NotFountException {
        repository.findById(EmployeeId).orElseThrow(NotFountException::new);
        repository.deleteById(EmployeeId);
    }

    public Employee update(final long EmployeeId, final Employee updatedEmployee)
            throws NotFountException {

        final Employee employee =
                repository.findById(EmployeeId).orElseThrow(NotFountException::new);
        employee.setSsn(updatedEmployee.getSsn());
        employee.setName(updatedEmployee.getName());
        employee.setLastName(updatedEmployee.getSecondLastName());
        employee.setBirthdate(updatedEmployee.getBirthdate());

        return repository.save(employee);
    }
}
