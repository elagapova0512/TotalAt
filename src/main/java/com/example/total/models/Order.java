package com.example.total.models;

import com.example.total.enumm.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="order_site")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;
@ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product; // 1 строчка в заказе - 1 товар
@ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Person person;

private int count; // quantity of goods in order
    private float totalSum;
    private LocalDateTime time; // время создания заказа
    @PrePersist
    public void init(){ // при создании заказа сработает этот метод
        time = LocalDateTime.now();
    }
    private Status status;

    public Order() {
    }

    public Order(String number, Product product, Person person, int count, float totalSum,Status status) {
        this.number = number;
        this.product = product;
        this.person = person;
        this.count = count;
        this.totalSum = totalSum;
       this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return number.equals(order.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
