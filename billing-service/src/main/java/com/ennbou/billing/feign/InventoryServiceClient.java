package com.ennbou.billing.feign;

import com.ennbou.billing.entities.Product;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryServiceClient {
    @RequestMapping(method = RequestMethod.GET,value="/products/{id}", produces = "application/json")
    Product getProductById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.GET,value="/products", produces = "application/json")
    PagedModel<Product> pageProducts(@RequestHeader("Authorization") String token);
}
