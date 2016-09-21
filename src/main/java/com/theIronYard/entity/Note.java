package com.theIronYard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by chris on 9/1/16.
 */
@Entity
public class Note {
    @Id
    @GeneratedValue
    private Integer id;

//    private Integer animal_id;
    private String content;
    private LocalDateTime date;
//    private Animal animal;

    public Note(String content) {
        this.content = content;
        this.date = LocalDateTime.now();
    }

    public Note(Integer id, String content, LocalDateTime date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public Integer getId() {return id;}

    public void setId(int id) {this.id = id;}

//    public int getAnimal_id() {return animal_id;}
//
//    public void setAnimal_id(Integer animal_id) {this.animal_id = animal_id;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDateTime getDate() {return date;}

    public String getFormattedDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        return date.format(format);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note notes = (Note) o;

        if (getId() != notes.getId()) return false;
        if (!getContent().equals(notes.getContent())) return false;
        return getDate().equals(notes.getDate());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return id + "\t" + dateTimeFormatter.format(date) + "\t" + content;
    }
}
