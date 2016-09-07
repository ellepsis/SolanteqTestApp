package com.ellepsis.solanteqTest.repository.specifications;

import com.ellepsis.solanteqTest.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.Date;

/**
 * Created by Ellepsis on 07.09.2016.
 */
public class EmployeeSpecifications {

    private static Specification<Employee> isPosition(Integer positionId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (positionId == null) return null;
            return criteriaBuilder.equal(root.get("position").get("id"), positionId);
        };
    }

    private static Specification<Employee> dateFrom(Date startDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (startDate == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("birthdayDate"), startDate);
        };
    }

    private static Specification<Employee> dateTo(Date endDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (endDate == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("birthdayDate"), endDate);
        };
    }

    private static Specification<Employee> isFirstNameStartsWith(String firstName) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (firstName == null) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        });
    }

    private static Specification<Employee> isLastNameStartsWith(String lastName) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (lastName == null) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        });
    }

    private static Specification<Employee> isMiddleNameStartsWith(String middleName) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (middleName == null) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")), "%" + middleName.toLowerCase() + "%");
        });
    }

    public static Specification<Employee> filter(String lastName, String firstName, String middleName,
                                                 Date startDate, Date endDate, Integer positionId) {
        return Specifications.where(
                isPosition(positionId))
                .and(isLastNameStartsWith(lastName))
                .and(isFirstNameStartsWith(firstName))
                .and(isMiddleNameStartsWith(middleName))
                .and(dateFrom(startDate))
                .and(dateTo(endDate));
    }
}
