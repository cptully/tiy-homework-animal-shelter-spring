package com.theIronYard.service;

import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

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




}
