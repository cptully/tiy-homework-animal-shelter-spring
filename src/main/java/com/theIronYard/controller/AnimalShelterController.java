package com.theIronYard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chris on 9/20/16.
 */
@Controller
public class AnimalShelterController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String animalShelter() {
        return "list";
    }
}