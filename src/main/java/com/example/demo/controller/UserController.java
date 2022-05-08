package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> read() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public Boolean delete(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    @PutMapping("/users")
    public User update(@RequestBody User newUser){
        if (userRepository.findById(newUser.getId()).isPresent()){
            User oldUser = userRepository.findById(newUser.getId()).get();
            oldUser.setEmail(newUser.getEmail());
            oldUser.setCityId(newUser.getCityId());
            oldUser.setName(newUser.getName());
            oldUser.setPhone(newUser.getPhone());
            return userRepository.save(oldUser);
        } else{
            return userRepository.save(newUser);
        }
    }

}
