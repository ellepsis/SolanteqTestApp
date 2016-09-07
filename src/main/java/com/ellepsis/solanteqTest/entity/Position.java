package com.ellepsis.solanteqTest.entity;

import javax.persistence.*;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@Entity
@Table(name = "employee_position")
public class Position {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "position_name")
    private String positionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
