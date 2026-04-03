package com.example.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.journalApp.entity.UserEntry;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId> {

    UserEntry findByUserName(String userName);

    void deleteByUserName(String username);

}