package com.theIronYard.controller;

import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Breed;
import com.theIronYard.entity.Note;
import com.theIronYard.entity.Type;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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




    @RequestMapping(path = "/")
    public String list(Model model,
                       String name,
                       Integer typeId,
                       Integer breedId,
                       Integer animalId, @PageableDefault(size = 15, sort = "name") Pageable pageable
                       ) {
        Page<Animal> animals =  animalRepository.findAll(pageable);
        Page<Breed> breeds = breedRepository.findAll(pageable);
        Page<Type> types = typeRepository.findAll(pageable);


        if ((name != null ) && (!name.equals(""))) {
            animals = animalRepository.findByName(name, pageable);
        } else if (animalId != null) {
            animals = animalRepository.findById(animalId, pageable);
        } else if (breedId != null) {
            animals = animalRepository.findByBreedId(breedId, pageable);
        } else if (typeId != null) {
            animals = animalRepository.findByTypeId(typeId, pageable);
//            List<Breed> tempBreeds = breedRepository.findByTypeId(typeId);
//            for (Breed breed : tempBreeds) {
//                animals.addAll(animalRepository.findByBreedId(breed.getId()));
//            }
        }

        model.addAttribute("animals", animals);
        model.addAttribute("breeds", breeds);
        model.addAttribute("types", types);
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
    @RequestMapping(path = "/deleteNote", method = RequestMethod.GET)
    public String deleteNote(Model model, Integer id, Integer animalId) {

        noteRepository.deleteById(id);

        Animal animal = animalRepository.findOne(animalId);

        return "redirect:/notes?id=" + animal.getId();
    }

    @RequestMapping(path = "/notes")
    public String notes(Model model, Integer id, String content) {
        Animal animal = animalRepository.findOne(id);
        model.addAttribute("animal", animal);
        if ((content != null) && (!content.equals(""))) {
            animal.addNote(content);
            animalRepository.save(animal);
        }
        return "notes";
    }

    @RequestMapping(path = "/addNote", method = RequestMethod.POST)
    public String addNote(@RequestParam Integer id,
                          @RequestParam LocalDate date,
                          @RequestParam String content) {
        //Note note = noteRepository.findOne(noteId);
        Note note = new Note(id, content, date);
        noteRepository.save(note);
        return "redirect:/note";
    }

    // TODO: 9/22/16 how much code to move out to animalService?

    // TODO: 9/22/16 implement search function
}