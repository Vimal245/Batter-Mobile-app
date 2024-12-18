package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/profiles")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
        
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getProfileById(@PathVariable String id) {
        return userService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateProfile(@PathVariable String id, @RequestBody UserModel updatedProfile) {
        return userService.getProfileById(id)
                .map(existingProfile -> {
                    existingProfile.setName(updatedProfile.getName());
                    existingProfile.setPhone(updatedProfile.getPhone());
                    existingProfile.setEmail(updatedProfile.getEmail());
                    existingProfile.setRole(updatedProfile.getRole());
                    existingProfile.setAddress(updatedProfile.getAddress());
                    UserModel savedProfile = userService.saveOrUpdateProfile(existingProfile);
                    return ResponseEntity.ok(savedProfile);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
