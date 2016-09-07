package com.ellepsis.solanteqTest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@Entity
@Table(name = "employee_position")
public class Position {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "position_name")
    private String positionName;

}
