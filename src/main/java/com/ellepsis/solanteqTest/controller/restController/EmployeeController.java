package com.ellepsis.solanteqTest.controller.restController;

import com.ellepsis.solanteqTest.entity.Employee;
import com.ellepsis.solanteqTest.repository.EmployeeRepository;
import com.ellepsis.solanteqTest.repository.specifications.EmployeeSpecifications;
import com.ellepsis.solanteqTest.responseAbstraction.ResponseWithCount;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@RestController
@RequestMapping(path = "/api/employee/")
public class EmployeeController {

    private final String DEFAULT_PAGE = "0";
    private final String DEFAULT_COUNT = "25";

    private EmployeeRepository employeeRepository;


    //This method is required for convert front end request get parameters
    // of type date from js representation to java internal format
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new ISO8601DateFormat();
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(path = "getEmployees", method = RequestMethod.GET)
    public ResponseWithCount<Employee> getAllEmployees(@RequestParam(required = false) String lastName,
                                          @RequestParam(required = false) String firstName,
                                          @RequestParam(required = false) String middleName,
                                          @RequestParam(required = false) Integer positionId,
                                          @RequestParam(required = false) Date startDate,
                                          @RequestParam(required = false) Date endDate,
                                          @RequestParam(required = false, defaultValue = DEFAULT_PAGE) Integer pageNumber,
                                          @RequestParam(required = false, defaultValue = DEFAULT_COUNT) Integer rowCount){
        Specification<Employee> employeeSpecification = EmployeeSpecifications.
                filter(lastName, firstName, middleName, startDate, endDate, positionId);
        if (pageNumber < 0) pageNumber = 0;
        if (rowCount < 1) rowCount = 1;
        PageRequest pageRequest = new PageRequest(pageNumber, rowCount);
        Page<Employee> page = employeeRepository.findAll(employeeSpecification, pageRequest);
        return new ResponseWithCount<>(page.getContent(), page.getTotalElements());
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
