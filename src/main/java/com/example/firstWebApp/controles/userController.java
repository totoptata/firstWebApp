package com.example.firstWebApp.controles;
import com.example.firstWebApp.services.userServices;
import com.example.firstWebApp.entities.user;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class userController {

    @Autowired
    private userServices userServices;
    @PostMapping("/users/addUser")
    public @ResponseBody user addUser(@RequestBody user u)
    {
        return userServices.addUser(u);
    }

    @GetMapping("/users/getAll")
    public @ResponseBody ArrayList<user> getAll()
    {
        return userServices.getAll();
    }

    @GetMapping("/user/findUserId/{id}")
    public @ResponseBody Optional<user> findUserById(@PathVariable long id)
    {
        return userServices.findUserById(id);
    }
    /*@PostMapping("/user/login")
    public @ResponseBody Optional<user> login(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password){
        return Optional.of(userServices.login(userName, password).orElseThrow());
    }*/
    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> userCredentials) {
        String UserName = userCredentials.get("UserName");
        String password = userCredentials.get("password");
        Optional<user> userOptional = userServices.login(UserName, password);
        return userOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
    }

  /*  @DeleteMapping("/user/deleteUserById/{id}")
    public @ResponseBody String deleteUserById(@PathVariable Long id)
    {
        userServices.deleteUserById(id);
        return "DONE!";
    }
   @PutMapping("/update/{id}")
    public user updateUser(@PathVariable("id") Long id, @RequestBody user updatedUser) {
        System.out.println("i am an udated user control=" + updatedUser.getUserName() + updatedUser.getEmail());

        userController userService = null;
        return userService.updateUser(id, updatedUser);
  }*/


    }
