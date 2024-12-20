package main.java.model;

import jakarta.validation.constraints.NotNull;
import solutions.own.instructor4j.annotation.Description;

public class Product {

    @Description("The unique identifier of the product")
    @NotNull
    private String id;

    @Description("The name of the product")
    @NotNull
    private String name;

    @Description("The price of the product")
    @NotNull
    private double price;

    public Product() {
    }

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", price=" + price +
            '}';
    }
}
