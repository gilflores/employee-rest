package org.directory.controller;

import org.directory.entity.Employee;
import org.directory.exception.NotFountException;
import org.directory.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    private static final Logger log = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
    
    public static final String BASE_PATH = "/v1/employees";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testEndPointAddEmployee() throws Exception {
        var birthdate = LocalDate.of(2000,11,8);

        Employee employeeParameter = new Employee(0,
                "574-81-XXXX",
                "Ismael",
                "Rodriguez",
                "Vasquez", birthdate);
        Employee savedEmployee = new Employee(1,
                "574-81-XXXX",
                "Ismael",
                "Rodriguez",
                "Vasquez", birthdate);

        when(service.add(employeeParameter)).thenReturn(savedEmployee);

        mvc.perform(post(BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employeeParameter).getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "ssn": "574-81-XXXX",
                            "name": "Ismael",
                            "lastName": "Rodriguez",
                            "secondLastName": "Vasquez",
                            "birthdate": "11/08/2000"
                        }"""));
    }

    @Test
    void testEndPointFindEmployee() throws Exception {
        LocalDate birthdate = LocalDate.of(2000,11,8);

        Employee savedEmployee = new Employee(1, "574-81-XXXX",
                "Ismael", "Rodriguez", "Vasquez", birthdate);

        when(service.findById(1)).thenReturn(savedEmployee);

        final String request = """
                {
                    "id": 1,
                    "ssn": "574-81-XXXX",
                    "name": "Ismael",
                    "lastName": "Rodriguez",
                    "secondLastName": "Vasquez",
                    "birthdate": "11/08/2000"
                }""";
        log.info(request);
        ResultActions result = mvc.perform(get(BASE_PATH + "/1")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON));
        log.info("Content: {}", content());
        result.andExpect(status().isOk())
                .andExpect(content().json(request));
    }

    @Test
    void testEndPointFindEmployeeNotExisting() throws Exception {
        when(service.findById(9999)).thenThrow(NotFountException.class);

        ResultActions result = mvc.perform(get(BASE_PATH + "/9999")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNoContent());
    }

    @Test
    void testEndPointGetEmployeesById() throws Exception {
        var birthdate = LocalDate.of(2000,11,8);

        Employee firstEmployee = new Employee(1, "574-81-XXXX", "Ismael",
                "Rodriguez", "Vasquez", birthdate);
        Employee secondEmployee = new Employee(2, "574-81-XXAA", "Maria Ximena",
                "Beck", "Ferriti", birthdate);

        List<Employee> employees = new ArrayList<>();
        employees.add(firstEmployee);
        employees.add(secondEmployee);

        when(service.findAll()).thenReturn(employees);

        mvc.perform(get(BASE_PATH).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "ssn": "574-81-XXXX",
                                "name": "Ismael",
                                "lastName": "Rodriguez",
                                "secondLastName": "Vasquez",
                                "birthdate": "11/08/2000"
                            },
                            {
                                "id": 2,
                                "ssn": "574-81-XXAA",
                                "name": "Maria Ximena",
                                "lastName": "Beck",
                                "secondLastName": "Ferriti",
                                "birthdate": "11/08/2000"
                            }
                        ]"""));
    }

    @Test
    void testEndPointDeleteById() throws Exception {
        mvc.perform(delete(BASE_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testEndPointDeleteByIdNotExisting() throws Exception {
        doThrow(new NotFountException()).when(service).deleteById(9999);

        mvc.perform(delete(BASE_PATH + "/9999")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEndPointUpdateEmployee() throws Exception {
        var birthdate = LocalDate.of(2000,11,8);

        Employee employeeParameter = new Employee(1, "574-81-XXXX", "Ismael",
                "Rodriguez", "Vazquez", birthdate);

        when(service.update(1, employeeParameter)).thenReturn(employeeParameter);

        ResultActions result = mvc.perform(put(BASE_PATH + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employeeParameter).getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
            .andExpect(content().json("""
                {
                    "id": 1,
                    "ssn": "574-81-XXXX",
                    "name": "Ismael",
                    "lastName": "Rodriguez",
                    "secondLastName": "Vazquez",
                    "birthdate": "11/08/2000"
                }"""));
    }

    @Test
    void testEndPointUpdateEmployeeINotExisting() throws Exception {
        final LocalDate birthdate = LocalDate.of(2000,11,8);

        Employee employeeParameter = new Employee(9999, "574-81-XXXX",
                "Ismael", "Rodriguez", "Vazquez", birthdate);

        when(service.update(9999, employeeParameter))
                .thenThrow(NotFountException.class);

        mvc.perform(put(BASE_PATH + "/9999")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employeeParameter).getBytes(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
