package com.theIronYard.service;

import com.theIronYard.entity.Note;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.BreedRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

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



    @RequestMapping(path = "/deleteNote", method = RequestMethod.GET)
    public String deleteNote(Integer id) {
        if (noteRepository.findById(id).size() == 0) {
            noteRepository.deleteById(id);
        }
        return "redirect:/note";
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
}
