package com.theIronYard.service;

import com.theIronYard.bean.Login;
import com.theIronYard.entity.User;
import com.theIronYard.repository.UserRepository;
import com.theIronYard.utility.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chris on 8/12/16.
 */
@Service
public class AnimalService {

    @Autowired
    UserRepository userRepository;

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
                userRepository.save(new User("Default Administrator", "admin@admin.com", PasswordStorage.createHash("password")));
            } catch (PasswordStorage.CannotPerformOperationException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> listUsers() {
        return userRepository.findAll();
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
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }
}





