package com.example.journalApp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.JournalEntryService;
import com.example.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUser(Authentication authentication) {
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);

        List<JournalEntry> allEntry = user.getJournalEntries();
        if (allEntry != null && !allEntry.isEmpty()) {
            return new ResponseEntity<>(allEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, Authentication authentication) {
        try {
            String userName = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId,
            Authentication authentication) {
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);

            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId, Authentication authentication) {
        String userName = authentication.getName();
        boolean removed = journalEntryService.deleteById(myId, userName);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry, Authentication authentication) {
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(id);
            if (journalEntry.isPresent()) {
                JournalEntry oldEntry = journalEntry.get();

                oldEntry.setTitle(newEntry.getTitle() != null &&
                        !newEntry.getTitle().equals("") ? newEntry.getTitle()
                                : oldEntry.getTitle());
                oldEntry.setContent(
                        newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                                : oldEntry.getContent());
                journalEntryService.saveEntry2(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}