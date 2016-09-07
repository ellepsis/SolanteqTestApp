package com.ellepsis.solanteqTest.controller.restController;

import com.ellepsis.solanteqTest.entity.Employee;
import com.ellepsis.solanteqTest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@RestController
@RequestMapping(path = "/api/employee/")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @RequestMapping(path = "getEmployees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
