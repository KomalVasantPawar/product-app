package com.example.product.resource;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @POST
    public Uni<Response> create(Product product) {
        return service.create(product).map(p -> Response.status(201).entity(p).build());
    }

    @GET
    public Uni<List<Product>> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getById(@PathParam("id") Long id) {
        return service.getById(id)
                .onItem().ifNotNull().transform(p -> Response.ok(p).build())
                .onItem().ifNull().continueWith(() -> Response.status(404).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, Product updated) {
        return service.update(id, updated)
                .map(p -> Response.ok(p).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return service.delete(id)
                .map(deleted -> deleted ? Response.noContent().build() : Response.status(404).build());
    }

    @GET
    @Path("/{id}/availability")
    public Uni<Response> checkAvailability(@PathParam("id") Long id, @QueryParam("count") int count) {
        return service.checkAvailability(id, count)
                .map(avail -> Response.ok(Map.of("available", avail)).build());
    }

    @GET
    @Path("/sorted")
    public Uni<List<Product>> getSortedByPrice() {
        return service.sortByPriceAsc();
    }
}