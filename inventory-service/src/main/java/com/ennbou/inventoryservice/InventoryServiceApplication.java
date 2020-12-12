package com.ennbou.inventoryservice;

import com.ennbou.inventoryservice.entities.Product;
import com.ennbou.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null, "Product 1 ", 900));
            productRepository.save(new Product(null, "Product 2", 80));
            productRepository.save(new Product(null, "Product 3", 1800));
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
