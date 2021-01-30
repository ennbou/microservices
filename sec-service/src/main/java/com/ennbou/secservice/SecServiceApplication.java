package com.ennbou.secservice;

import com.ennbou.secservice.entities.AppRole;
import com.ennbou.secservice.entities.AppUser;
import com.ennbou.secservice.repositories.RoleRepository;
import com.ennbou.secservice.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SecServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository users, RoleRepository roles) {
        return args -> {
            AppUser admin,user;
            admin = new AppUser(null, "admin","1234",new ArrayList<>());
            user = new AppUser(null, "user","1234",new ArrayList<>());

            AppRole adminRole = new AppRole(null, "ADMIN");
            AppRole userRole = new AppRole(null, "USER");

            roles.save(adminRole);
            roles.save(userRole);

            admin.getRoles().add(adminRole);
            admin.getRoles().add(userRole);

            user.getRoles().add(userRole);

            users.save(admin);
            users.save(user);



        };
    }

}
