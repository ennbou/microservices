package com.ennbou.billing.entities;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
}
