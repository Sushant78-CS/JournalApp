package com.example.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("id/{myId}")
    public UserEntry getUserById(@PathVariable ObjectId myId) {
        return userService.getUserById(myId).orElse(null);
    }

    @DeleteMapping()
    public boolean deleteUserById(Authentication authentication) {
        String username = authentication.getName();
        userService.deleteUserByName(username);
        return true;
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry, Authentication authentication) {
        String username = authentication.getName();
        UserEntry userIdDb = userService.findByUserName(username);
        if (userIdDb != null) {
            userIdDb.setUserName(
                    userEntry.getUserName() != null && !userEntry.getUserName().equals("") ? userEntry.getUserName()
                            : userIdDb.getUserName());
            userIdDb.setPassword(
                    userEntry.getPassword() != null && !userEntry.getPassword().equals("") ? userEntry.getPassword()
                            : userIdDb.getPassword()); 

            userService.createNewUser(userIdDb);
            return new ResponseEntity<>(userIdDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}