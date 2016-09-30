package com.theIronYard.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by chris on 9/7/16.
 */
@Entity
@Transactional
public class Breed {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @ManyToOne
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
