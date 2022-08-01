package com.auth.auth.util;

import com.auth.auth.repository.RoleRepository;
import com.auth.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoad implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public DataLoad(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Roles: ");
        roleRepository.findAll().forEach(System.out::println);

        System.out.println("Users: ");
        userRepository.findAll().forEach(System.out::println);
    }
}
