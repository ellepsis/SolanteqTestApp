package com.ellepsis.solanteqTest.repository;

import com.ellepsis.solanteqTest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jca.cci.core.InteractionCallback;
import org.springframework.stereotype.Repository;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
