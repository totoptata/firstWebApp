package com.example.firstWebApp.services;

import com.example.firstWebApp.entities.user;
import com.example.firstWebApp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class userServices {
    @Autowired
   private userRepository repository;


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
    public Optional<user> login(String UserName,String password) {
        Optional<user> u = repository.login(UserName,password);

        return u;
    }

    public void  deleteUserById(Long id)
    {
      repository.deleteAllById(Collections.singleton(id));
    }
  /* public user updateUser(Long id, user updateduser) {

     System.out.println("i am an udated user="+ updateduser.getUserName()+updateduser.getEmail());
       user existinguser = repository.findById(id)
               .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
     existinguser.setUserName(updateduser.getUserName());
        existinguser.setEmail(updateduser.getEmail());
        existinguser.setFirstName(updateduser.getFirstName());
        existinguser.setSecondName(updateduser.getSecondName());
        existinguser.setAddress(updateduser.getAddress());
        existinguser.setPhoneNumber(updateduser.getPhoneNumber());
        existinguser.setPassword(updateduser.getPassword());
        return repository.save(existinguser);
    }*/
    }

