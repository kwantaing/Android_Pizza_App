package com.example.pizzaapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails implements Serializable {
    String PizzaSize;
    double sizePrice;
    double topping_subtotal = 0;
    double grand_total = 0;
    List<Topping> Toppings = new ArrayList<Topping>();




    public void calculateTotal(){
        this.topping_subtotal = 0;
        for (Topping topping: Toppings) {
            this.topping_subtotal+=topping.price;
        }
        this.grand_total = this.topping_subtotal + this.sizePrice;
    }
}
