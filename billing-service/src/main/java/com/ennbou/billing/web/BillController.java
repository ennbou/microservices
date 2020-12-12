package com.ennbou.billing.web;


import com.ennbou.billing.dao.BillRepository;
import com.ennbou.billing.feign.CustomerServiceClient;
import com.ennbou.billing.feign.InventoryServiceClient;
import com.ennbou.billing.dao.ProductItemRepository;
import com.ennbou.billing.entities.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceClient customerServiceClient;
    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @GetMapping("/bills/full/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.getCustomerById(bill.getCustomerid()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(inventoryServiceClient.getProductById(pi.getId()));
        });

        return bill;
    }
}

