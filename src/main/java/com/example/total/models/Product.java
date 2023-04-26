package com.example.total.models;

import com.example.total.enumm.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name",nullable = false,columnDefinition = "text",unique = true)
    @NotEmpty(message = "Поле не может быть пустым")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    @Column(name="description",nullable = false,columnDefinition = "text")
    private  String description;
    @Min(value=1)
    @Column(nullable = false, name = "price")
    private float price;
    @Column(name = "color")
    private String color;
    @Column(nullable = false, name = "category")
    private Category category;
@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
private List<Image> imageList = new ArrayList<>();
public void addImageToProduct(Image image){
    image.setProduct(this); // это для какого товара предназначена данная фотография
    imageList.add(image);
}
@ManyToMany
@JoinTable(name="product_cart",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="person_id"))
private List<Person> personList;
@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
private List<Order> orderList;

    public Product(String name, String description, float price, String color, Category category, List<Image> imageList) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.category = category;
        this.imageList = imageList;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
