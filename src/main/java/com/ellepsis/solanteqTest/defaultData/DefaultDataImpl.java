package com.ellepsis.solanteqTest.defaultData;

import com.ellepsis.solanteqTest.entity.Employee;
import com.ellepsis.solanteqTest.entity.Position;
import com.ellepsis.solanteqTest.repository.EmployeeRepository;
import com.ellepsis.solanteqTest.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Ellepsis on 08.09.2016.
 */
@Service
public class DefaultDataImpl implements DefaultData {

    private EmployeeRepository employeeRepository;

    private PositionRepository positionRepository;

    @Override
    public void insertData(){
        Position position = new Position();
        position.setPositionName("Водитель");
        position.setId(1);
        positionRepository.save(position);

        position = new Position();
        position.setPositionName("Директор");
        position.setId(2);
        positionRepository.save(position);

        position = new Position();
        position.setPositionName("Продавец");
        position.setId(3);
        positionRepository.save(position);

        Calendar calendar = new GregorianCalendar();

        Employee employee = new Employee();
        employee.setId(1);
        employee.setBirthdayDate(new Date(System.currentTimeMillis()));
        employee.setFirstName("FirstName");
        employee.setMiddleName("MiddleName");
        employee.setLastName("LastName");
        employee.setPosition(positionRepository.findOne(3));
        employeeRepository.save(employee);

        calendar.set(1952, 2, 23);
        employee = new Employee();
        employee.setId(2);
        employee.setBirthdayDate(new Date(calendar.getTimeInMillis()));
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setPosition(positionRepository.findOne(2));
        employeeRepository.save(employee);

        calendar.set(1992, 12, 11);
        employee = new Employee();
        employee.setId(3);
        employee.setBirthdayDate(new Date(calendar.getTimeInMillis()));
        employee.setFirstName("Петр");
        employee.setMiddleName("Александрович");
        employee.setLastName("Иванов");
        employee.setPosition(positionRepository.findOne(3));
        employeeRepository.save(employee);

        calendar.set(1980, 3, 1);
        employee = new Employee();
        employee.setId(4);
        employee.setBirthdayDate(new Date(calendar.getTimeInMillis()));
        employee.setFirstName("Александр");
        employee.setMiddleName("Владимирович");
        employee.setLastName("Сергеев");
        employee.setPosition(positionRepository.findOne(1));
        employeeRepository.save(employee);
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

