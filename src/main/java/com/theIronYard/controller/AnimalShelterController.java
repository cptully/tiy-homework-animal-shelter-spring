package com.theIronYard.controller;

import com.theIronYard.bean.Login;
import com.theIronYard.bean.Search;
import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Breed;
import com.theIronYard.entity.Type;
import com.theIronYard.entity.User;
import com.theIronYard.service.AnimalService;
import com.theIronYard.utility.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@Controller
public class AnimalShelterController {

    @Autowired
    AnimalService animalService;

    @RequestMapping(path = "/")
    public String list(Model model,
                       Search search,
                       @PageableDefault(size = 15, sort = "name") Pageable pageable,
                       String action) {
        if ((action != null) && (action.equals("clear"))) {
            search = new Search();
        }
        Page<Animal> animals = animalService.listAnimals(search, pageable);
        List<Breed> breeds = animalService.breedList();
        List<Type> types = animalService.typeList();

        model.addAttribute("animals", animals);
        model.addAttribute("breeds", breeds);
        model.addAttribute("types", types);
        model.addAttribute("pageable", pageable);
        model.addAttribute("search", search);
        return "list";
    }

    @RequestMapping(path = "/addAnimal", method = RequestMethod.GET)
    public String addAnimal(Model model, @RequestParam(defaultValue = "") Integer id) {
        List<Type> types = animalService.typeList();
        Animal animal;
        List<Breed> breeds;

        if (id != null) {
            animal = animalService.getOne(id);
            breeds = animalService.breedList(animal.getBreed().getType().getId());
        } else {
            animal = new Animal();
            breeds = animalService.breedList();
        }

        model.addAttribute("animal", animal);
        model.addAttribute("types", types);
        model.addAttribute("breeds", breeds);
        return "addAnimal";
    }

    @RequestMapping(path = "/editAnimal", method = RequestMethod.POST)
    public String editAnimal(@Valid Animal animal,
                            BindingResult bindingResult,
                            String action) {
        if (action.equals("save")) {
            animalService.addAnimal(animal);
        } else if (action.equals("delete")) {
            animalService.deleteAnimal(animal);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/breed", method = RequestMethod.GET)
    public String breed(Model model) {
        List<Breed> breeds =  animalService.breedList();
        List<Type> types = animalService.typeList();
        model.addAttribute("breeds", breeds);
        model.addAttribute("types", types);
        return "breed";
    }
    
    @RequestMapping(path = "/deleteBreed", method = RequestMethod.GET)
    public String deleteBreed(Integer id) {
        Boolean success = animalService.deleteBreed(id);
        return "redirect:/breed";
    }

    @RequestMapping(path = "/addBreed", method = RequestMethod.POST)
    public String addBreed(@RequestParam String breedName,
                           @RequestParam Integer typeId) {
        animalService.addBreed(breedName, typeId);
        return "redirect:/breed";
    }

    @RequestMapping(path = "/type", method = RequestMethod.GET)
    public String type(Model model) {
        List<Type> types =  animalService.typeList();
        model.addAttribute("types", types);
        return "type";
    }

    @RequestMapping(path = "/addType", method = RequestMethod.POST)
    public String addType(@RequestParam String typeName) {
        animalService.addType(typeName);
        return "redirect:/type";
    }

    @RequestMapping(path = "/deleteType", method = RequestMethod.GET)
    public String deleteType(Integer id) {
        animalService.deleteType(id);
        return "redirect:/type";
    }

    @RequestMapping(path = "/deleteNote", method = RequestMethod.GET)
    public String deleteNote(Model model, Integer id, Integer animalId) {
        Animal animal = animalService.deleteNote(animalId, id);

        model.addAttribute("animal", animal);

        return "redirect:/notes?id=" + animal.getId();
    }

    @RequestMapping(path = "/notes")
    public String notes(Model model, Integer id, String content) {
        Animal animal = animalService.getOne(id);
        model.addAttribute("animal", animal);
        if ((content != null) && (!content.equals(""))) {
            animal.addNote(content);
            animalService.addAnimal(animal);
        }
        return "notes";
    }

    @RequestMapping(path = "/addNote", method = RequestMethod.POST)
    public String addNote(@RequestParam Integer id,
                          @RequestParam LocalDate date,
                          @RequestParam String content) {
        animalService.addNote(id, date, content);
        return "redirect:/note";
    }

    public void afterInit() {
        animalService.createDefaultAdminUser();
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Login login, Model model){

        model.addAttribute("login", login);

        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@Valid Login login, HttpSession session){

        User user = animalService.authenticateUser(login);

        if(user != null){
            session.setAttribute("userId", user.getId());
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/users")
    public String users(Model model, HttpSession session){
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        model.addAttribute("users", animalService.listUsers());

        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "users";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registration(Integer id, Model model, HttpSession session){

        model.addAttribute("editUser", animalService.getUser(id));

        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute(name = "editUser") User editUser, BindingResult bindingResult, Model model, HttpSession session){

        if(bindingResult.hasErrors()){

            model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

            return "users";
        } else {
            try {
                animalService.saveUser(editUser);
                return "redirect:/users";
            } catch (PasswordStorage.CannotPerformOperationException e) {
                editUser.setPassword("");
                return "users";
            }
        }
    }

    @RequestMapping(path = "/deleteUser")
    public String deleteUser(Integer id, HttpSession session){

        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        animalService.deleteUser(id);

        return "redirect:/users";
    }
}