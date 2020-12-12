package com.ennbou.customerservice;

import com.ennbou.customerservice.entities.Customer;
import com.ennbou.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "name1", "email1@gmail.com"));
            customerRepository.save(new Customer(null, "name2", "email2@gmail.com"));
            customerRepository.save(new Customer(null, "name3", "email3@gmail.com"));
            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
