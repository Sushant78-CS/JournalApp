package com.example.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.JournalEntryRepository;
import com.example.journalApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntries, String userName) {
        try {
            UserEntry user = userService.findByUserName(userName);
            JournalEntry journalEntry = journalEntryRepository.save(journalEntries);
            user.getJournalEntries().add(journalEntry);
            userService.createUser2(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving entry.", e);
        }
    }

    public void saveEntry2(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            UserEntry user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.createUser2(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error occured", e);
            throw new RuntimeException("An error occured while deleting the entry.", e);
        }
        return removed;
    }

}