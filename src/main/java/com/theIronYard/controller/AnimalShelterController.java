package com.theIronYard.controller;

import com.theIronYard.bean.Login;
import com.theIronYard.bean.Search;
import com.theIronYard.entity.*;
import com.theIronYard.service.AnimalService;
import com.theIronYard.utility.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
                       String action,
                       HttpSession session) {
        if ((action != null) && (action.equals("clear"))) {
            search = new Search();
        }

        // get the list of animals from the requested search
        Page<Animal> animals = animalService.listAnimals(search, pageable);

        // get lists of breeds and types for teh drop lists
        List<Breed> breeds = animalService.breedList();
        List<Type> types = animalService.typeList();

        // populate the model
        model.addAttribute("animals", animals);
        model.addAttribute("breeds", breeds);
        model.addAttribute("types", types);
        model.addAttribute("pageable", pageable);
        model.addAttribute("search", search);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "list";
    }

    @RequestMapping(path = "/addAnimal", method = RequestMethod.GET)
    public String addAnimal(Model model,
                            @RequestParam(defaultValue = "") Integer id,
                            HttpSession session) {

        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        // get the list of animal types
        List<Type> types = animalService.typeList();

        // empty objects to be set later
        Animal animal;
        List<Breed> breeds;


        // if an animal is being edited get it's data
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

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "addAnimal";
    }

    @RequestMapping(path = "/editAnimal", method = RequestMethod.POST)
    public String editAnimal(Model model,
                             @Valid Animal animal,
                             BindingResult bindingResult,
                             String action,
                             Integer breedId,
                             HttpSession session) {

        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        if (action.equals("save")) {
            Breed breed = animalService.getBreedById(breedId);
            animal.setBreed(breed);
            animalService.addAnimal(animal);
        } else if (action.equals("delete")) {
            animalService.deleteAnimal(animal);
        }

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/";
    }

    @RequestMapping(path = "/breed", method = RequestMethod.GET)
    public String breed(Model model, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        List<Breed> breeds =  animalService.breedList();
        List<Type> types = animalService.typeList();
        model.addAttribute("breeds", breeds);
        model.addAttribute("types", types);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "breed";
    }
    
    @RequestMapping(path = "/deleteBreed", method = RequestMethod.GET)
    public String deleteBreed(Model model, Integer id, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        Boolean success = animalService.deleteBreed(id);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/breed";
    }

    @RequestMapping(path = "/addBreed", method = RequestMethod.POST)
    public String addBreed(Model model,
                           @RequestParam String breedName,
                           @RequestParam Integer typeId,
                           HttpSession session) {

        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        animalService.addBreed(breedName, typeId);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/breed";
    }

    @RequestMapping(path = "/type", method = RequestMethod.GET)
    public String type(Model model, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        List<Type> types =  animalService.typeList();
        model.addAttribute("types", types);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "type";
    }

    @RequestMapping(path = "/addType", method = RequestMethod.POST)
    public String addType(Model model, @RequestParam String typeName, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        animalService.addType(typeName);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/type";
    }

    @RequestMapping(path = "/deleteType", method = RequestMethod.GET)
    public String deleteType(Model model, Integer id, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        animalService.deleteType(id);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/type";
    }

    @RequestMapping(path = "/deleteNote", method = RequestMethod.GET)
    public String deleteNote(Model model, Integer id, Integer animalId, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        Animal animal = animalService.deleteNote(animalId, id);

        model.addAttribute("animal", animal);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/notes?id=" + animal.getId();
    }

    @RequestMapping(path = "/notes")
    public String notes(Model model, Integer id, String content, HttpSession session) {
        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        Animal animal = animalService.getOne(id);
        model.addAttribute("animal", animal);
        if ((content != null) && (!content.equals(""))) {
            animal.addNote(content);
            animalService.addAnimal(animal);
        }

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "notes";
    }

    @RequestMapping(path = "/addNote", method = RequestMethod.POST)
    public String addNote(Model model,
                          @RequestParam Integer id,
                          @RequestParam LocalDate date,
                          @RequestParam String content,
                          HttpSession session) {

        // the user must be logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        animalService.addNote(id, content);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "redirect:/note";
    }

    public void afterInit() {
        animalService.createDefaultAdminUser();
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Login login, Model model, HttpSession session){

        model.addAttribute("login", login);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(Model model, @Valid Login login, HttpSession session){

        User user = animalService.authenticateUser(login);

        // get the user (or null if not logged in)
        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

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

    @RequestMapping(path = "/editUser", method = RequestMethod.GET)
    public String userForm(Integer id, Model model, HttpSession session){

        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        }

        model.addAttribute("editUser", animalService.getUser(id));

        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "registration";
    }

    @RequestMapping(path = "/editUser", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute(name = "editUser") User editUser,
                           BindingResult bindingResult,
                           Model model,
                           HttpSession session){

        model.addAttribute("user", animalService.getUser(editUser.getId()));

        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registration(Integer id, Model model, HttpSession session){

        model.addAttribute("editUser", animalService.getUser(id));

        model.addAttribute("user", animalService.getUserOrNull((Integer)session.getAttribute("userId")));

        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registration(@Valid User user,
                               BindingResult bindingResult,
                               @RequestParam(defaultValue = "") String oldPassword,
                               @RequestParam(defaultValue = "") String confimPassword,
                               Model model,
                               HttpSession session){
        if(!bindingResult.hasErrors()){

            try {
                if (user.getId() != null) {
                    String savedPassword = animalService.getUser(user.getId()).getPassword();
                    if (PasswordStorage.verifyPassword(oldPassword, savedPassword)) {
                        if (!PasswordStorage.verifyPassword(user.getPassword(), savedPassword)) {
                            if (user.getPassword().equals(confimPassword)) {
                                animalService.saveUser(user);
                                return "redirect:/list";
                            } else {
                                // set errors
                                FieldError fieldError = new FieldError("user", "password", user.getPassword(), false, new String[]{"Invalid.user.password"}, (String[])null, "Passwords do not match");
                                bindingResult.addError(fieldError);
                            }
                        } else {
                            // new matches old
                            FieldError fieldError = new FieldError("user", "password", user.getPassword(), false, new String[]{"Invalid.user.password"}, (String[])null, "New password cannot match old password");
                            bindingResult.addError(fieldError);
                        }
                    } else {
                        // invalid password
                        FieldError fieldError = new FieldError("user", "password", user.getPassword(), false, new String[]{"Invalid.user.password"}, (String[])null, "Username / password combination incorrect");
                        bindingResult.addError(fieldError);
                    }
                } else {
                    animalService.saveUser(user);
                    return "redirect:/users";
                }
            } catch (PasswordStorage.CannotPerformOperationException e) {
                user.setPassword("");
//                return "resitratino";
            } catch (PasswordStorage.InvalidHashException e) {
                e.printStackTrace();
            }
        }

        if(bindingResult.hasErrors()){

            model.addAttribute("user", user);
            model.addAttribute("bindingResult", bindingResult);
//            return "registration";
        }
        return "registration";
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