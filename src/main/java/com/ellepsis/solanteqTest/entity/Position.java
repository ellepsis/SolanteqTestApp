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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (id != null ? !id.equals(position.id) : position.id != null) return false;
        return positionName != null ? positionName.equals(position.positionName) : position.positionName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
        return result;
    }
}
