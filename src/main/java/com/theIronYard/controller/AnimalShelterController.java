package com.theIronYard.controller;

import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Breed;
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
        return "list";
    }

    @RequestMapping(path = "/addAnimal", method = RequestMethod.GET)
    public String addAnimal(Model model, @RequestParam(defaultValue = "") Integer id) {
        List<Type> types = typeRepository.findAll();
        Animal animal;
        List<Breed> breeds;

        if (id != null) {
            animal = animalRepository.getOne(id);
            breeds = breedRepository.findByTypeName(animal.getBreed().getType().getName());
        } else {
            animal = new Animal();
            breeds = breedRepository.findAll();
        }

        model.addAttribute("animal", animal);
        model.addAttribute("types", types);
        model.addAttribute("breeds", breeds);
        return "addAnimal";
    }

    @RequestMapping(path = "/saveAnimal", method = RequestMethod.POST)
    public String addAnimal(@RequestParam (defaultValue = "-1") Integer id,
                            @RequestParam String name,
                            @RequestParam Integer typeId,
                            @RequestParam Integer breedId,
                            @RequestParam String color,
                            @RequestParam String description,
                            @RequestParam String action) {
        if (action.equals("save")) {
            Breed breed = breedRepository.getOne(breedId);
//        Type type = typeRepository.getOne(typeId);
            Animal animal;

            if (animalRepository.exists(id)) {
                animal = new Animal(id, name, breed, color, description);
            } else {
                animal = new Animal(name, breed, color, description);
            }
            // breedRepository.save(breed);
            animalRepository.saveAndFlush(animal);
        } else if (action.equals("delete")) {
            animalRepository.delete(id);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/breed", method = RequestMethod.GET)
    public String breed(Model model) {
        List<Breed> breeds =  breedRepository.findAll();
        List<Type> types = typeRepository.findAll();
        model.addAttribute("breeds", breeds);
        model.addAttribute("types", types);
        return "breed";
    }
    
    @RequestMapping(path = "/deleteBreed", method = RequestMethod.GET)
    public String deleteBreed(Integer id) {
        if(animalRepository.findByBreedId(id).size() == 0) {
            breedRepository.deleteById(id);
        }
        return "redirect:/breed";
    }

    @RequestMapping(path = "/addBreed", method = RequestMethod.POST)
    public String addBreed(@RequestParam String breedName,
                           @RequestParam Integer typeId) {
        Type type = typeRepository.findOne(typeId);
        Breed breed = new Breed(breedName, type);
        breedRepository.save(breed);
        return "redirect:/breed";
    }

    @RequestMapping(path = "/type", method = RequestMethod.GET)
    public String type(Model model) {
        List<Type> types =  typeRepository.findAll();
        model.addAttribute("types", types);
        return "type";
    }

    @RequestMapping(path = "/addType", method = RequestMethod.POST)
    public String addType(@RequestParam String typeName) {
        Type type = new Type(typeName);
        typeRepository.save(type);
        return "redirect:/type";
    }

    @RequestMapping(path = "/deleteType", method = RequestMethod.GET)
    public String deleteType(Integer id) {
        if(breedRepository.findByTypeId(id).size() == 0) {
            typeRepository.deleteById(id);
        }
        return "redirect:/type";
    }

    // TODO: 9/22/16 add, edit & delete methods for note

    // TODO: 9/22/16 how much code to move out to animalService?

    // TODO: 9/22/16 implement search function
}