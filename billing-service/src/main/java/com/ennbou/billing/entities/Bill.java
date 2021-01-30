package com.ennbou.billing.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingdate;
    private double total;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private Long customerid;
    @Transient private Customer customer;
}
