package com.example.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // private static final Logger logger =
    // LoggerFactory.getLogger(UserService.class);

    public List<UserEntry> getUser() {
        return userRepository.findAll();
    }

    public boolean createNewUser(UserEntry userEntry) {
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("User"));
            userRepository.save(userEntry);
            return true;
        } catch (Exception e) {
            log.info("Hello Sumit");
            log.error("Error occured for {}", userEntry.getUserName(), e);
            log.warn("Hello Jituraj");
            log.debug("Hello Ansh");
            log.trace("Hello Rudra");
            return false;
        }
    }

    public void createUser2(UserEntry userEntry) {
        userRepository.save(userEntry);
    }

    public UserEntry saveAdmin(UserEntry userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("User", "ADMIN"));
        return userRepository.save(userEntry);
    }

    public Optional<UserEntry> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public UserEntry findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean deleteUser(ObjectId myid) {
        userRepository.deleteById(myid);
        return true;
    }

    public boolean deleteUserByName(String username) {
        userRepository.deleteByUserName(username);
        return true;
    }

}