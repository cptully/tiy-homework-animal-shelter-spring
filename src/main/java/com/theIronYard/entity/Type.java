package com.theIronYard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chris on 9/7/16.
 */
@Entity
public class Type {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Type(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
        this.id = 0;
        this.name = "";
    }
    public Type(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return id;
    }

    public void setTypeId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return name;
    }

    public void setTypeName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type that = (Type) o;

        if (getTypeId() != that.getTypeId()) return false;
        return getTypeName().equals(that.getTypeName());

    }

    @Override
    public int hashCode() {
        int result = getTypeId();
        result = 31 * result + getTypeName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
