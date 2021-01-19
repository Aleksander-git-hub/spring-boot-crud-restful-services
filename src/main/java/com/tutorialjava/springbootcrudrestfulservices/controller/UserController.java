package com.tutorialjava.springbootcrudrestfulservices.controller;

import com.tutorialjava.springbootcrudrestfulservices.entity.User;
import com.tutorialjava.springbootcrudrestfulservices.exception.ResourceNotFoundException;
import com.tutorialjava.springbootcrudrestfulservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{

    @Autowired
    private UserRepository userRepository;

    // get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found By id: " + id));
    }

    // create user
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // update user
    @PutMapping("/{id}")
    public User updateUser(@Valid @RequestBody User user,
                           @PathVariable(value = "id") long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found By id: " + userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found By id: " + userId));
        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
