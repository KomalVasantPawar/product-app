package com.example.product.service;

import com.example.product.entity.Product;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;

import java.util.List;
@ApplicationScoped
public class ProductService {

     @WithTransaction
     public Uni<Product> create(Product product) {
        return product.persist();
    }

    public Uni<List<Product>> getAll() {
        return Product.listAll();
    }

    public Uni<Product> getById(Long id) {
        return Product.findById(id);
    }

    public Uni<Product> update(Long id, Product updated) {
        return getById(id)
                .onItem().ifNotNull().invoke(p -> {
                    p.name = updated.name;
                    p.description = updated.description;
                    p.price = updated.price;
                    p.quantity = updated.quantity;
                });
    }

    @WithTransaction
    public Uni<Boolean> delete(Long id) {
        return Product.deleteById(id);
    }

    public Uni<Boolean> checkAvailability(Long id, int count) {
        return getById(id)
                .onItem().ifNotNull().transform(p -> p.quantity >= count);
    }

    public Uni<List<Product>> sortByPriceAsc() {
        return Product.list("order by price asc");
    }
}