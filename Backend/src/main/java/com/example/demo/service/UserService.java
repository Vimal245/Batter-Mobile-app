package com.example.demo.service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public Optional<UserModel> getProfileById(String id) {
        return userRepository.findById(id);
    }

    public UserModel saveOrUpdateProfile(UserModel profile) {
        return userRepository.save(profile);
    }
}
