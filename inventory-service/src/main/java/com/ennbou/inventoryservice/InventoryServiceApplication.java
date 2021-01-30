package com.ennbou.inventoryservice;

import com.ennbou.inventoryservice.entities.Product;
import com.ennbou.inventoryservice.repositories.ProductRepository;
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
	
	@Bean
public RepositoryRestConfigurer repositoryRestConfigurer()
{
    return RepositoryRestConfigurer.withConfig(config -> {
        config.exposeIdsFor(Product.class);
    });
}

}


@RestController
class ProductController{

  private final ProductRepository productRepository;

  ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
  @DeleteMapping("/products/delete")
  public List<Long> delete(@RequestParam(name="ids") List<Long> ids){
    productRepository.deleteAll(productRepository.findAllById(ids));
    return ids;
  }
}
