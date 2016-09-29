package com.theIronYard.service;

<<<<<<< HEAD
import com.theIronYard.bean.Search;
import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Breed;
import com.theIronYard.entity.Type;
=======
import com.theIronYard.entity.Animal;
>>>>>>> e6679d0d0330e60970c7ec448060e86431f9e474
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
<<<<<<< HEAD
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> e6679d0d0330e60970c7ec448060e86431f9e474

/**
 * Created by chris on 8/12/16.
 */
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


/*    public List<Animal> listAnimals(){
        return animalRepository.findAll();
    }

    public Page<Animal> listAnimals(Search search, Pageable pageable) {
        Page<Animal> animals;
        if ((search.getName() != null ) && (!search.getName().equals(""))) {
            animals.clear();
            animals = animalRepository.findByName(search.getName());
        } else if (search.getAnimalId() != null) {
            Animal animal = animalRepository.findOne(search.getAnimalId());
            animals.clear();
            animals.add(animal);
        } else if (search.getBreedId() != null) {
            animals.clear();
            animals = animalRepository.findByBreedId(search.getBreedId());
        } else if (search.getTypeId() != null) {
            animals.clear();
            List<Breed> tempBreeds = breedRepository.findByTypeId(search.getTypeId());
            for (Breed breed : tempBreeds) {
                animals.addAll(animalRepository.findByBreedId(breed.getId()));
            }
        }
        return new ArrayList<>();
    }

    public List<Breed> listBreeds(){
        return breedRepository.findAll();
    }

<<<<<<< HEAD
    public List<Type> listTypes(){
        return typeRepository.findAll();
    }*/
=======


>>>>>>> e6679d0d0330e60970c7ec448060e86431f9e474
}
