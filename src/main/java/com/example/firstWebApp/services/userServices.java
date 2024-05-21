package com.example.firstWebApp.services;

import com.example.firstWebApp.entities.user;
import com.example.firstWebApp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class userServices {
    @Autowired
    userRepository repository;

    public user addUser(user u)
    {
        return repository.save(u);
    }
    public ArrayList<user> getAll()
    {
        return (ArrayList<user>) repository.findAll();
    }
    public Optional<user> findUserById(Long id)
    {
        return repository.findById(id);
    }
    public Optional<user> login(String UserName , String password)
    {
        return repository.login(UserName,password);
    }
    public user updateUser(Long id, user updateduser) {

      System.out.println("i am an udated user="+ updateduser.getUserName()+updateduser.getEmail());
        Optional<user> existinguser = repository.findById(id);
        existinguser.orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existinguser.get().setUserName(updateduser.getUserName());
        existinguser.get().setEmail(updateduser.getEmail());
         existinguser.get().setPassword(updateduser.getPassword());
         return repository.save(existinguser.get());

        //return updateduser;
    }


        @Autowired
        private com.example.firstWebApp.repository.userRepository.UserRepository userRepository;

        public void deleteUserById(Long id) throws UserNotFoundException {
            Optional<user> userOptional = userRepository.findUserById(id);
            if (userOptional.isPresent()) {
                userRepository.deleteById(id);
            } else {
                throw new UserNotFoundException("User with id " + id + " not found");
            }
        }



        public static class UserNotFoundException extends Throwable {
            public UserNotFoundException(String ignoredS) {
            }
        }
    }





