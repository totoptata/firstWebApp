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
    private static userServices userServices;

    @PostMapping("/users/addUser")
    public @ResponseBody user addUser(@RequestBody user u) {
        return userServices.addUser(u);
    }

    @GetMapping("/users/getAll")
    public @ResponseBody ArrayList<user> getAll() {
        return userServices.getAll();
    }

    @GetMapping("/user/findUserId/{id}")
    public @ResponseBody Optional<user> findUserById(@PathVariable("id") long id) {
        return userServices.findUserById(id);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> userCredentials) {
        String UserName = userCredentials.get("UserName");
        String password = userCredentials.get("password");
        Optional<user> userOptional = userServices.login(UserName, password);
        return userOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
    }

    @PutMapping("/update/{id}")
    public user updateUser(@PathVariable("id") Long id, @RequestBody user updatedUser) {
        System.out.println("i am an udated user control=" + updatedUser.getUserName() + updatedUser.getEmail());

        return userServices.updateUser(id, updatedUser);
    }
@DeleteMapping("/user/deleteUserById/{id}")
    public @ResponseBody String deleteUserById(@PathVariable Long id) throws com.example.firstWebApp.services.userServices.UserNotFoundException {
        userServices.deleteUserById(id);
        return "DONE!";
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws com.example.firstWebApp.services.userServices.UserNotFoundException {
        try {
            Optional<user> userOptional = userServices.findUserById(id);
            if (userOptional.isPresent()) {
                userServices.deleteUserById(id);
                return ResponseEntity.ok().body("User deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
        }
    }
    @RestController
    public static class UserController {



        @DeleteMapping("/user/delete/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
            try {
                userServices.deleteUserById(id);
                return ResponseEntity.ok().body("User deleted successfully.");
            } catch (com.example.firstWebApp.services.userServices.UserNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
            }
        }
    }


}
