package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    OrderDetails Order = new OrderDetails();

    List<CheckBox> topping_checkboxes = new ArrayList<CheckBox>();
    Dictionary<String,Double> all_toppings = new Hashtable<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populate_toppingsDict();
        createToppingCheckBoxList();
    }

    public void populate_toppingsDict(){
        all_toppings.put("Pepperoni",1.99);
        all_toppings.put("Chicken",1.99);
        all_toppings.put("Olive",0.99);
        all_toppings.put("Green Pepper",0.49);
        all_toppings.put("Mushroom",0.99);
        all_toppings.put("Extra Cheese",0.99);
    }

    public void createToppingCheckBoxList() {
        topping_checkboxes.add((CheckBox) findViewById(R.id.topping_1));
        topping_checkboxes.add((CheckBox) findViewById(R.id.topping_2));
        topping_checkboxes.add((CheckBox) findViewById(R.id.topping_3));
        topping_checkboxes.add((CheckBox) findViewById(R.id.topping_4));
        topping_checkboxes.add((CheckBox) findViewById(R.id.topping_5));
        topping_checkboxes.add((CheckBox) findViewById(R.id.topping_6));

    }

    public void onClickUpdateSizePrice(View view) {
        RadioGroup size_radio = (RadioGroup) findViewById(R.id.size_radio);
        int selected_size_id = size_radio.getCheckedRadioButtonId();
        RadioButton selectedSizeButton = (RadioButton) findViewById(selected_size_id);
        String pizza_size = selectedSizeButton.getText().toString();
        Order.PizzaSize = pizza_size;
        switch(pizza_size){
            case "Small":
                Order.sizePrice = 5.99;
                break;
            case "Medium":
                Order.sizePrice = 8.99;
                break;
            case "Large":
                Order.sizePrice = 11.99;
                break;
            default:
                Order.sizePrice = 8.99;
                break;
        }
        Toast.makeText(this, selectedSizeButton.getText().toString() + " size selected", Toast.LENGTH_SHORT).show();
    }

    public void onClickToggleTopping(View view){
        CheckBox checkbox = (CheckBox) view;
        String topping_name = checkbox.getText().toString();
        if(checkbox.isChecked()){
            Order.Toppings.add(new Topping(topping_name,all_toppings.get(topping_name)));
            Toast.makeText(this,"Added: "+topping_name,Toast.LENGTH_SHORT).show();
//            Toast.makeText(this,"Topping Count: "+ Order.Toppings.size(),Toast.LENGTH_SHORT).show();

        }else{
            if(!Order.Toppings.isEmpty()){
                for(Topping existingTopping : Order.Toppings) {
                    if(existingTopping.topping_Name == topping_name){
                        Order.Toppings.remove(existingTopping);
                        Toast.makeText(this, "Removed: "+topping_name,Toast.LENGTH_SHORT).show();
//                        Toast.makeText(this,"Topping Count: "+ Order.Toppings.size(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    }


    public void continueOrder(View view) {
        Intent intent = new Intent(this,Checkout.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ORDER",Order);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}