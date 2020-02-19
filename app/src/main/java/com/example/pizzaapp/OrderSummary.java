package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        OrderDetails Order = (OrderDetails) getIntent().getSerializableExtra("ORDER");
        Customer Customer = (Customer) getIntent().getSerializableExtra("CUSTOMER");
        show_orderDetails(Order);
        show_customerDetails(Customer);
    }

    public void show_orderDetails(OrderDetails Order) {
        TextView details = findViewById(R.id.Details);
        TextView totals = findViewById(R.id.tvTotal);
        String totalsText ="$ \t"+ Order.sizePrice+"\n\n";
        String deets = "Pizza size: "+ Order.PizzaSize +"\n" + "Toppings: " + "\n";

        for(Topping topping : Order.Toppings){
            deets += "\t -" + topping.topping_Name+"\n";
            totalsText += "$ \t" + topping.price+"\n";
        }

        Order.calculateTotal();
        deets+= "\nGrand Total:";
        totalsText  += "\n$ \t" + Order.grand_total+"\n";
        details.setText(deets);
        totals.setText(totalsText);
    }

    public void show_customerDetails(Customer Customer){
        TextView labels = (TextView) findViewById(R.id.customer_info_label);
        TextView cust_details = (TextView) findViewById(R.id.customer_details);
        String labelsText = "Customer Name: \n\n" + "Phone Number: \n\n" + "Email: \n\n" + "Address: \n\n";
        String detailsText= Customer.name+"\n\n" +Customer.phone_number+ "\n\n"+Customer.email+"\n\n" + Customer.address+"\n\n";
        labels.setText(labelsText);
        cust_details.setText(detailsText);
    }
}
