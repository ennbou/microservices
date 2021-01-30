package com.ennbou.secservice.web;

import com.ennbou.secservice.entities.AppUser;
import com.ennbou.secservice.repositories.RoleRepository;
import com.ennbou.secservice.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public List<AppUser> users(){
        return userRepository.findAll();
    }

    @PostMapping("/roles")
    public void addRole(@RequestBody String roleName){

    }

}
