package com.dharma.boot.bean;

import javax.persistence.*;

@Entity
@Table(name="product")
public class EProduct {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Double price;

    public EProduct() {
    }

    public EProduct(String name, Double price) {
        this.id = -1;
        this.name = name;
        this.price = price;
    }

    public EProduct(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
