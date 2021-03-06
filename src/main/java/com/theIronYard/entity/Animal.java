package com.theIronYard.entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 8/12/16.
 */
@Entity
public class Animal {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String name;
    private String description;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    private Breed breed;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private List<Note> notes;

    /**
     * entity constructor which creates a new animal with the specified properties.
     *
     * @param name String
     * @param breed String
     * @param color String
     * @param description String
     */
    public Animal(Integer id, String name, Breed breed, String color, String description) {
        this.id = id;
        this.name = name;
        this.breed = new Breed(breed.getId(), breed.getName(), breed.getType());
        this.color = color;
        this.description = description;
        this.notes = new ArrayList<>();
    }

    public Animal(String name, Breed breed, String color, String description) {
        this.name = name;
        this.breed = new Breed(breed.getId(), breed.getName(), breed.getType());
        this.color = color;
        this.description = description;
        this.notes = new ArrayList<>();
    }

    /**
     * entity no arguments constructor creates a blank animal.
     */
    public Animal() {
        this.name = "";
        this.breed = new Breed();
        this.color = "";
        this.description = "";
        this.notes = new ArrayList<>();
    }

    // getter methods
    public String getName(){ return this.name; }
    public Breed getBreed(){ return this.breed; }
    public String getDescription(){ return this.description; }
    public String getColor(){ return this.color; }
    public Integer getId() {return id;}
    public List<Note> getNotes() {return notes;}
    public Integer getNoteCount() {return notes.size();}


    /*
        The setter methods are package private because AnimalRepository exposes methods
        for creating and editing animals.
         */
    public void setName(String name){ this.name = name; }
    public void setBreed(Breed breed){ this.breed = breed; }
    public void setDescription(String description){ this.description = description; }
    public void setColor(String color){ this.color = color; }
    public void setId(Integer id) {this.id = id;}
    public void setNotes(ArrayList<Note> notes) {
        // create blank list
        this.notes = new ArrayList<>();

        // addAnimal notes to list instead of creating a link
        this.notes.addAll(notes);
    }
    public void addNote(Note note) {this.notes.add(note);}
    public void addNote(String note) {
        Note newNote = new Note(note);
        this.notes.add(newNote);
    }

    @Override
    public String toString() {
       return this.id + "\t" + this.name + "\t" + this.breed.getName() + "\t" + this.color;
    }

    /**
     * toString(verbose) Overloads the toString method with an option to
     * display the full details of the entity. Including the description.
     *
     * @param verbose String, ignored, but it's presence triggers this
     *                version of the toString method.
     * @return String
     */
    public String toString(String verbose){
        return "Name:\t\t\t" + this.name + "\nType:\t\t\t" +
                "\nBreed:\t\t\t" + this.breed.getName() + "\nColor:\t\t\t" + this.color + "\nDescription:\t" + this.description;
    }
}
