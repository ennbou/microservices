package com.ennbou.customerservice;

import com.ennbou.customerservice.entities.Customer;
import com.ennbou.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
//@EnableDiscoveryClient
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

  @Bean
  public RepositoryRestConfigurer repositoryRestConfigurer() {
    return RepositoryRestConfigurer.withConfig(config -> {
      config.exposeIdsFor(Customer.class);
    });
  }

}

@RestController
class CustomerController{

  private final CustomerRepository customerRepository;

  CustomerController(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  @DeleteMapping("/customers/delete")
  public List<Long> delete(@RequestParam(name="ids") List<Long> ids){
    customerRepository.deleteAll(customerRepository.findAllById(ids));
    return ids;
  }
}
