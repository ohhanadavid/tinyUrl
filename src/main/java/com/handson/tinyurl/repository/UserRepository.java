package com.handson.tinyurl.repository;

import com.handson.tinyurl.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findFirstByName(String name);

}

