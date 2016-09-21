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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
