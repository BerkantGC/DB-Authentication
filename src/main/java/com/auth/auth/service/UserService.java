package com.auth.auth.service;

import com.auth.auth.model.User;
import com.auth.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public void save(String... args) {
         List<Role> admin = new ArrayList<>();
         User berkant = new User(1L,"Berkant", "123456", 0);

         List<User> users = Arrays.asList(berkant);

         userRepository.saveAll(users);
     }*/

    public List<User> getAll() {
         List<User> users = userRepository.findAll();

         return users;
    }

    public User handleRegister(User user){
        User userToSave = new User();
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userToSave.setBlocked(user.getBlocked());
        userToSave.setRoles(user.getRoles());
        System.out.println(userToSave);
        return userRepository.save(userToSave);
    }
}
