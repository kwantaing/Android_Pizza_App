
package com.example.pizzaapp;

import java.io.Serializable;

class Topping implements Serializable {

    String topping_Name;
    double price ;
    boolean selected;


    public Topping(String name, double price) {
        this.topping_Name = name;
        this.price = price;
        this.selected = false;
    }
}
