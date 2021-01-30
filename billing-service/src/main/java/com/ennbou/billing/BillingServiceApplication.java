package com.ennbou.billing;

import com.ennbou.billing.dao.BillRepository;
import com.ennbou.billing.dao.ProductItemRepository;
import com.ennbou.billing.entities.Bill;
import com.ennbou.billing.entities.Customer;
import com.ennbou.billing.entities.Product;
import com.ennbou.billing.entities.ProductItem;
import com.ennbou.billing.feign.CustomerServiceClient;
import com.ennbou.billing.feign.InventoryServiceClient;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    final String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0dUQ5UkJhaFVGbHpISHNqOEZMZjBHRWk0OGdjeks5RW1tX2tUNWwyM1BZIn0.eyJleHAiOjE2MTA3NjQ0MjMsImlhdCI6MTYxMDc2MDgyMywianRpIjoiOGU3ZGE0NTItZGY0NS00ODA0LWFmN2MtOGIzMjgzNzI2MWZiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2FjaGF0LWVuLWxpZ25lIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjE0MWU1ODRiLWM5ZTItNDQzOC1iMWRiLWFmMjM2NzI4OGE5YiIsInR5cCI6IkJlYXJlciIsImF6cCI6InRlc3QiLCJzZXNzaW9uX3N0YXRlIjoiMjA3ZTA1MmEtYjkxMS00OTIyLThjOWQtNDVmNzc0ODZjNzY3IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODEiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIkJJTExJTkdfTUFOQUdFUiIsIlBST0RVQ1RfTUFOQUdFUiIsIm9mZmxpbmVfYWNjZXNzIiwiQ1VTVE9NRVJfTUFOQUdFUiIsInVtYV9hdXRob3JpemF0aW9uIiwiQURNSU4iLCJVU0VSIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiQm91Y2hhaWIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJib3VjaGFpYiIsImdpdmVuX25hbWUiOiJCb3VjaGFpYiJ9.J0mnd5h1eP7dSai7mcKQJgGNGO7X41zPY4ErNG35JKQWMasePtAioEfnRg0KBau2VWV1kkARUJBt9IRQlcIMfjqip-vz1kR9RL8zH40FMZBjH_EUj7IENu5uvTCtd1qFAZ23RWn_PZ_kmHy3ccqKooJR1YpHURxa1PIBCttwXcGidtVX9b83B6Q3-FgRtIFAWoAHZU-GulwuWvDZLBxi34AS1nCBCsL8txt252FEOICA39oGaS-S-hYL1NHeArysMG7zUIbJq9EupXvwEX7Wf5X7URBbhIApSRKW1wbsjDXygtIqJFcG3_uceXmIYjWM8JIuCySKo60wpOjjF2iZnA";

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient){
        return args -> {

//            System.out.println("**************************");
//            Customer customer=customerServiceClient.getCustomerById(1L);
//            Bill bill1=billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
//            PagedModel<Product> productPagedModel=inventoryServiceClient.pageProducts(token);
//            productPagedModel.forEach(p->{
//                ProductItem productItem=new ProductItem();
//                productItem.setPrice(p.getPrice());
//                productItem.setQuantity(1+new Random().nextInt(100));
//                productItem.setBill(bill1);
//                productItem.setProductID(p.getId());
//                productItemRepository.save(productItem);
//            });
            //billRepository.findAll().forEach(System.out::println);
//            System.out.println("**************************");
        };
    }
}

//@Component
//class FeignClientInterceptor implements RequestInterceptor {
//
//    private static final String AUTHORIZATION_HEADER="Authorization";
//    private static final String TOKEN_TYPE = "Bearer";
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
//            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, details.getTokenValue()));
//        }
//    }
//}
