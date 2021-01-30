package com.ennbou.billing.feign;

import com.ennbou.billing.entities.Customer;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerServiceClient {
    @RequestMapping(method = RequestMethod.GET, value="/customers/{id}", produces = "application/json")
    Customer getCustomerById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
}
