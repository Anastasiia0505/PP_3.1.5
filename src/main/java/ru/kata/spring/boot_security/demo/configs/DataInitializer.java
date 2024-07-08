package ru.kata.spring.boot_security.demo.configs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Configuration
public class DataInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

@Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User adminUser = new User("admin", passwordEncoder.encode("admin"));
        adminUser.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
        userRepository.save(adminUser);

        User simpleUser = new User("user", passwordEncoder.encode("user"));
        simpleUser.setRoles(Collections.singleton(userRole));
        userRepository.save(simpleUser);
    }
}