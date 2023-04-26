package com.example.total.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity // что будет подключаться к БД
@Table//что подключается к существующей таблице

public class Person {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Поле логин не может быть пустым")
    @Size(min = 5, max = 100, message = "Логин должен быть от 5 до 100 символов")
    private String login;
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2)
    private String surname;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2)
    private String name;
    @Min(value=18, message = "Возраст должен быть больше 18")
    private int age;
    @NotEmpty(message = "Поле не может быть пустым")
    @Email(message = "Вы ввели не адрес электронной почты")
    private String email;
    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})+$",message = "+7/7/8 1234567891")
    private String phone;

    private String role;
    @ManyToMany
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name="person_id"),inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> productList;
@OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List <Order> orderList;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person(String role) {
        this.role = role;
    }

    public Person(String login, String password, String surname, String name, int age, String email, String phone) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
