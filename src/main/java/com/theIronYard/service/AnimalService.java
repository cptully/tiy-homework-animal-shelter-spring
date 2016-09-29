package com.theIronYard.service;

import com.theIronYard.bean.Search;
import com.theIronYard.entity.*;
import com.theIronYard.entity.Animal;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 8/12/16.
 */
@Service
public class AnimalService {
    // properties
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    TypeRepository typeRepository;


    public List<Animal> listAnimals(){
        return animalRepository.findAll();
    }

    public List<Breed> breedList() {
        return breedRepository.findAll();
    }

    public List<Breed> breedList(Integer typeId) {
        return breedRepository.findByTypeId(typeId);
    }

    public List<Type> typeList() {
        return typeRepository.findAll();
    }

    public Page<Animal> listAnimals(Search search, Pageable pageable) {
        Page<Animal> animals =  animalRepository.findAll(pageable);

        if ((search.getName() != null ) && (!search.getName().equals(""))) {
            animals = animalRepository.findByName(search.getName(), pageable);
        } else if (search.getAnimalId() != null) {
            animals = animalRepository.findById(search.getAnimalId(), pageable);
        } else if (search.getBreedId() != null) {
            animals = animalRepository.findByBreedId(search.getBreedId(), pageable);
        } else if (search.getTypeId() != null) {
            animals = animalRepository.findByTypeId(search.getTypeId(), pageable);
        }
        return animals;
    }

    public Animal getOne(Integer id) {
        return animalRepository.getOne(id);
    }

    public void addAnimal(Animal animal) {
        animalRepository.saveAndFlush(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal.getId());
    }

    public void addBreed(String name, Integer typeId) {
        Type type = typeRepository.findOne(typeId);
        Breed breed = new Breed(name, type);
        breedRepository.save(breed);
    }

    public boolean deleteBreed(Integer id) {
        if(animalRepository.findByBreedId(id).size() == 0) {
            breedRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addType(String typeName) {
        Type type = new Type(typeName);
        typeRepository.save(type);
    }

    public boolean deleteType(Integer id) {
        if(animalRepository.findByTypeId(id).size() == 0) {
            typeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Animal deleteNote(Integer animalId, Integer noteId) {
        noteRepository.deleteById(noteId);
        return animalRepository.findOne(animalId);
    }

    public void addNote(Integer animalId, LocalDate date, String content) {
        Note note = new Note(animalId, content, date);
        noteRepository.save(note);
    }
}
