package com.ennbou.billing.feign;

import com.ennbou.billing.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryServiceClient {
    @RequestMapping(method = RequestMethod.GET,value="/products/{id}", produces = "application/json")
    Product getProductById(@PathVariable("id") Long id);
    @RequestMapping(method = RequestMethod.GET,value="/products", produces = "application/json")
    PagedModel<Product> pageProducts();
}
