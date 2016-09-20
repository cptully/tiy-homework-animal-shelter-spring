package com.theIronYard.entity;

import javax.persistence.*;

/**
 * Created by chris on 9/7/16.
 */
@Entity
public class Breed {
    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;

    public Breed(Integer breedId, String name, Type type) {
        this.id = breedId;
        this.name = name;
        this.type = type;
    }
    public Breed(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Breed() {
        this.type = new Type();
        this.name = "";
        this.id = 0;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Breed that = (Breed) o;

        if (getId() != that.getId()) return false;
        if (getType() != that.getType()) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
