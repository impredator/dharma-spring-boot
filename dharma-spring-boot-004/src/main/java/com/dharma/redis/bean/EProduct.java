package com.dharma.redis.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product")
public class EProduct implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

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
