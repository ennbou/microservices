package com.ennbou.billing.web;


import com.ennbou.billing.dao.BillRepository;
import com.ennbou.billing.dao.ProductItemRepository;
import com.ennbou.billing.entities.Bill;
import com.ennbou.billing.entities.Customer;
import com.ennbou.billing.entities.Product;
import com.ennbou.billing.entities.ProductItem;
import com.ennbou.billing.feign.CustomerServiceClient;
import com.ennbou.billing.feign.InventoryServiceClient;
import com.google.common.util.concurrent.AtomicDouble;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BillController {
  private final BillRepository billRepository;
  private final ProductItemRepository productItemRepository;
  private final CustomerServiceClient customerServiceClient;
  private final InventoryServiceClient inventoryServiceClient;

  public BillController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient) {
    this.billRepository = billRepository;
    this.productItemRepository = productItemRepository;
    this.customerServiceClient = customerServiceClient;
    this.inventoryServiceClient = inventoryServiceClient;
  }

  @GetMapping("/bills/full")
  public List<Bill> getAll(HttpServletRequest request){

    String token = "Bearer " + getToken(request);

    return billRepository.findAll().stream().
            peek(b -> b.setCustomer(customerServiceClient.getCustomerById(b.getCustomerid(), token))).
            collect(Collectors.toList());
  }

  @GetMapping("/bills/full/{id}")
  public Bill getBill(@PathVariable(name = "id") Long id, HttpServletRequest request) {

    String token = "Bearer " + getToken(request);

    Bill bill = billRepository.findById(id).get();
    System.out.println("customer ID : " + bill.getCustomerid());
    bill.setCustomer(customerServiceClient.getCustomerById(bill.getCustomerid(), token));

    return bill;
  }

  @PostMapping("/billing/{id}")
  public Bill add(@PathVariable(name = "id") Long id, @RequestBody List<BillForm> form, HttpServletRequest request) {

    String token = "Bearer " + getToken(request);

    Customer customer = customerServiceClient.getCustomerById(id, token);
    PagedModel<Product> productsSelected = inventoryServiceClient.pageProducts(token);
    Bill bill1 = billRepository.save(new Bill(null, new Date(), 0.0,new ArrayList<>(), customer.getId(), customer));

    Map<Long, Long> r = form.stream().collect(Collectors.toMap(b -> b.productsId, b -> b.qte));
    System.out.println("------ r");
    r.forEach((k,v) ->{
      System.out.println(k+" : "+v);
    });

    AtomicDouble total = new AtomicDouble(0.0);

    productsSelected.forEach(p -> {
      if (r.containsKey(p.getId())) {
        System.out.println("id : "+ p.getId());
        ProductItem productItem = new ProductItem();
        productItem.setPrice(p.getPrice());
        productItem.setQuantity(r.get(p.getId()));
        productItem.setBill(bill1);
        productItem.setProductID(p.getId());
        total.addAndGet(productItem.getPrice()*productItem.getQuantity());
        bill1.getProductItems().add(productItemRepository.save(productItem));
      }
    });
    bill1.setTotal(total.get());
    billRepository.save(bill1);


    return bill1;
  }

  public String getToken(HttpServletRequest request) {
    KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
    KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
    KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
    return context.getTokenString();
  }
}

class BillForm {
  public Long productsId;
  public Long qte;
}
