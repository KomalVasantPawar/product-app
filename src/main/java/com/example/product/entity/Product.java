package com.example.product.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Product extends PanacheEntity {
    public String name;
    public String description;
    public Double price;
    public Integer quantity;
}