package com.example.pizzaapp;

import java.io.Serializable;

public class Customer implements Serializable {
    String name;
    String phone_number;
    String email;
    String address;

    public Customer(String name, String phone_number, String email,String address){
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;

    }
}
