package com.ellepsis.solanteqTest;

import com.ellepsis.solanteqTest.entity.Employee;
import com.ellepsis.solanteqTest.entity.Position;
import com.ellepsis.solanteqTest.repository.EmployeeRepository;
import com.ellepsis.solanteqTest.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@SpringBootApplication
public class SolanteqTest  implements CommandLineRunner{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(SolanteqTest.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        Position position = new Position();
        position.setPositionName("PositionTest");
        position.setId(1);
        positionRepository.save(position);

        Employee employee = new Employee();
        employee.setId(1);
        employee.setBirthdayDate(new Date(System.currentTimeMillis()));
        employee.setFirstName("FirstName");
        employee.setMiddleName("MiddleName");
        employee.setLastName("LastName");
        employee.setPosition(position);

        employeeRepository.save(employee);
    }
}
