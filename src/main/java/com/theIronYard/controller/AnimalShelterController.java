package com.theIronYard.controller;

import com.sun.tools.javac.comp.Todo;
import com.sun.xml.internal.bind.v2.TODO;
import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Breed;
import com.theIronYard.entity.Note;
import com.theIronYard.entity.Type;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * Created by chris on 9/20/16.
 */
@Controller
public class AnimalShelterController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    TypeRepository typeRepository;



    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String list(Model model) {
        List<Animal> animals =  animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "/list";
    }

    @RequestMapping(path = "/addAnimal", method = RequestMethod.GET)
    public String addAnimal(Model model, @RequestParam(defaultValue = "") Integer id) {
        List<Type> types = typeRepository.findAll();
        Animal animal;
        List<Breed> breeds;
        Set<Note> notes;

        if (id != null) {
            animal = animalRepository.getOne(id);
            breeds = breedRepository.findByTypeName(animal.getBreed().getType().getName());
            notes = animal.getNotes();
        } else {
            animal = new Animal();
            breeds = breedRepository.findAll();
            notes = null;
        }

        model.addAttribute("animal", animal);
        model.addAttribute("types", types);
        model.addAttribute("breeds", breeds);
        model.addAttribute("notes", notes);
        return "/addAnimal";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(Model model,
                            @RequestParam Integer id,
                            @RequestParam String name,
                            @RequestParam Integer typeId,
                            @RequestParam Integer breedId,
                            @RequestParam String color,
                            @RequestParam String description) {
        Breed breed = breedRepository.getOne(breedId);
        Type type = typeRepository.getOne(typeId);
        // TODO: 9/22/16 if type and breed don't match we have a problem - ignored for now
        Animal animal;

        if (animalRepository.exists(id)) {
            animal = new Animal(id, name, breed, color, description);
        } else {
            animal = new Animal(name, breed, color, description);
        }
        animalRepository.save(animal);

        return "/list";
    }

    @RequestMapping(path = "/breed", method = RequestMethod.GET)
    public String breed(Model model) {
        List<Breed> breeds =  breedRepository.findAll();
        model.addAttribute("breeds", breeds);
        return "/breed";
    }


    @RequestMapping(path = "/breed", method = RequestMethod.POST)
    public String deleteBreed(Model model, @RequestParam(defaultValue = "") Integer id) {
        Breed breed = breedRepository.findOne(id);
        if(animalRepository.findByBreed(id).size() == 0) {
            breedRepository.deleteById(id);
        } else {

        }
        return "/breed";
    }

    // TODO: 9/22/16 addBreed method

    // TODO: 9/22/16 add & delete methods for type
    @RequestMapping(path = "/type", method = RequestMethod.GET)
    public String type(Model model) {
        List<Type> types =  typeRepository.findAll();
        model.addAttribute("types", types);
        return "/type";
    }

    // TODO: 9/22/16 add, edit & delete methods for note

    // TODO: 9/22/16 how much code to move out to animalService?

    // TODO: 9/22/16 implement search function
}