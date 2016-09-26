package com.theIronYard.service;

import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Breed;
import com.theIronYard.entity.Type;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.TypeRepository;
import com.theIronYard.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    NoteRepository noteRepository;

    public List<Animal> listAnimals() {
        return animalRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public List<Type> listTypes() {
        return typeRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public List<Breed> listBreeds() {
        return breedRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }


}
