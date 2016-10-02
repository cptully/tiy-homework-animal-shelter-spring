package com.theIronYard.service;

import com.theIronYard.bean.Login;
import com.theIronYard.bean.Search;
import com.theIronYard.entity.*;
import com.theIronYard.repository.*;
import com.theIronYard.utility.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chris on 8/12/16.
 */
@Service
public class AnimalService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Animal> listAnimals(){
        return animalRepository.findAll();
    }

    public List<Breed> breedList() {
        return breedRepository.findAll();
    }

    public Breed getBreedById(Integer breedId) {
        return breedRepository.findOne(breedId);
    }

    public List<Breed> breedList(Integer typeId) {
        return breedRepository.findByTypeId(typeId);
    }

    public List<Type> typeList() {
        return typeRepository.findAll();
    }

    public Page<Animal> listAnimals(Search search, Pageable pageable) {
        return animalRepository.search(search.getNameForSearch(), search.getBreedId(), search.getTypeId(), search.getAnimalId(), pageable);
    }

    public Animal getOne(Integer id) {
        return animalRepository.getOne(id);
    }

    public void addAnimal(Animal animal) {
        animalRepository.saveAndFlush(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal.getId());
    }

    public void addBreed(String name, Integer typeId) {
        Type type = typeRepository.findOne(typeId);
        Breed breed = new Breed(name, type);
        breedRepository.save(breed);
    }

    public boolean deleteBreed(Integer id) {
        if(animalRepository.findByBreedId(id).size() == 0) {
            breedRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addType(String typeName) {
        Type type = new Type(typeName);
        typeRepository.save(type);
    }

    public boolean deleteType(Integer id) {
        if(animalRepository.findByTypeId(id).size() == 0) {
            typeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Animal deleteNote(Integer animalId, Integer noteId) {
        noteRepository.deleteById(noteId);
        return animalRepository.findOne(animalId);
    }

    public void addNote(Integer animalId, String content) {
        Animal animal =animalRepository.getOne(animalId);
        Note note = new Note(content);
        animal.addNote(note);
        animalRepository.save(animal);
    }

    public User getUser(Integer id) {
        if(id != null) {
            return userRepository.findOne(id);
        } else {
            return new User();
        }
    }

    public User getUserOrNull(Integer id){
        if(id != null) {
            return userRepository.findOne(id);
        } else {
            return null;
        }
    }

    public User authenticateUser(Login login) {

        try {
            User user = userRepository.findByEmail(login.getEmail());

            if(user != null && PasswordStorage.verifyPassword(login.getPassword(), user.getPassword())){
                return user;
            }
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException e) {
            e.printStackTrace();
        }
        login.setLoginFailed(true);
        return null;
    }

    public void createDefaultAdminUser() {
        if(userRepository.count() == 0){
            try {
                User defaultAdmin = new User("Default Administrator", "admin@admin.com", PasswordStorage.createHash("password"));
                Role role = roleRepository.findByNameEquals("administrator");
                defaultAdmin.setRole(role);
                userRepository.save(defaultAdmin);
            } catch (PasswordStorage.CannotPerformOperationException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public List<User> listUsers(Integer id) {
        User user = userRepository.findOne(id);
        return userRepository.findAll(Example.of(user));
    }

    public void saveUser(User user) throws PasswordStorage.CannotPerformOperationException {

        if(user.getId() != null){
            User oldUser = userRepository.findOne(user.getId());

            if(!oldUser.getPassword().equals(user.getPassword())){
                user.setPassword(PasswordStorage.createHash(user.getPassword()));
            }
        } else {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
        }
        Role role = roleRepository.findByNameEquals("user");
        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }
}





