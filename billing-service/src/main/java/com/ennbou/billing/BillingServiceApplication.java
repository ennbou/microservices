package com.ennbou.billing;

import com.ennbou.billing.dao.BillRepository;
import com.ennbou.billing.dao.ProductItemRepository;
import com.ennbou.billing.entities.Bill;
import com.ennbou.billing.entities.Customer;
import com.ennbou.billing.entities.Product;
import com.ennbou.billing.entities.ProductItem;
import com.ennbou.billing.feign.CustomerServiceClient;
import com.ennbou.billing.feign.InventoryServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient){
        return args -> {
            System.out.println("**************************");
            Customer customer=customerServiceClient.getCustomerById(1L);
            Bill bill1=billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
            PagedModel<Product> productPagedModel=inventoryServiceClient.pageProducts();
            productPagedModel.forEach(p->{
                ProductItem productItem=new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill1);
                productItem.setProductID(p.getId());
                productItemRepository.save(productItem);
            });
            //billRepository.findAll().forEach(System.out::println);
            System.out.println("**************************");
        };
    }


}
