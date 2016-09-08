package com.ellepsis.solanteqTest.controller.restController;

import com.ellepsis.solanteqTest.entity.Employee;
import com.ellepsis.solanteqTest.exception.InvalidDataException;
import com.ellepsis.solanteqTest.repository.EmployeeRepository;
import com.ellepsis.solanteqTest.repository.PositionRepository;
import com.ellepsis.solanteqTest.repository.specifications.EmployeeSpecifications;
import com.ellepsis.solanteqTest.responseAbstraction.ResponseWithCount;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.DateFormat;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@RestController
@RequestMapping(path = "/api/employee/")
public class EmployeeController {

    private final String DEFAULT_PAGE = "0";
    private final String DEFAULT_COUNT = "25";

    private final int MAX_FIRST_NAME_SIZE = 40;
    private final int MAX_LAST_NAME_SIZE = 40;
    private final int MAX_MIDDLE_NAME_SIZE = 40;

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;


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
                                                       @RequestParam(required = false, defaultValue = DEFAULT_COUNT) Integer rowCount) {
        Specification<Employee> employeeSpecification = EmployeeSpecifications.
                filter(lastName, firstName, middleName, startDate, endDate, positionId);
        if (pageNumber < 0) pageNumber = 0;
        if (rowCount < 1) rowCount = 1;
        PageRequest pageRequest = new PageRequest(pageNumber, rowCount);
        Page<Employee> page = employeeRepository.findAll(employeeSpecification, pageRequest);
        return new ResponseWithCount<>(page.getContent(), page.getTotalElements());
    }

    @RequestMapping(path = "addNewEmployee/", method = RequestMethod.POST)
    public void addNewEmployee(@RequestBody Employee employee) {
        employee = checkEmployee(employee);
        employeeRepository.save(employee);
    }

    private Employee checkEmployee(Employee employee) {
        if (employee.getId() < 0) throw new InvalidDataException("Incorrect input id");
        if (employee.getFirstName() == null)
            throw new InvalidDataException("Employee first name field can't be empty");
        if (employee.getLastName() == null)
            throw new InvalidDataException("Employee last name field can't be empty");
        if (employee.getFirstName().length() > MAX_FIRST_NAME_SIZE)
            throw new InvalidDataException("Employee name must contain less than " + MAX_FIRST_NAME_SIZE + " symbols");
        if (employee.getLastName().length() > MAX_LAST_NAME_SIZE)
            throw new InvalidDataException("Employee name must contain less than " + MAX_LAST_NAME_SIZE + " symbols");
        if (employee.getMiddleName() != null && employee.getMiddleName().length() > MAX_MIDDLE_NAME_SIZE)
            throw new InvalidDataException("Employee name must contain less than " + MAX_MIDDLE_NAME_SIZE + " symbols");
        if (employee.getBirthdayDate() == null)
            throw new InvalidDataException("Employee birthday date can't be empty");
        if (employee.getBirthdayDate().after(new Date()))
            throw new InvalidDataException("Employee birthday date can't be after current time");
        if (employee.getPosition() == null)
            throw new InvalidDataException("Employee position can't be empty");
        if (!positionRepository.findOne(employee.getPosition().getId()).equals(employee.getPosition())){
            throw new InvalidDataException("Employee position is invalid");
        }
        return employee;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
