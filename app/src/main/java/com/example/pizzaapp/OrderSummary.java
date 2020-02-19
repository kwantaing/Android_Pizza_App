package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class OrderSummary extends AppCompatActivity {

    OrderDetails Order;
    Customer Customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        this.Order = (OrderDetails) getIntent().getSerializableExtra("ORDER");
        this.Customer = (Customer) getIntent().getSerializableExtra("CUSTOMER");
        show_orderDetails(Order);
        show_customerDetails(Customer);
    }

    public void show_orderDetails(OrderDetails Order) {
        TextView details = findViewById(R.id.Details);
        TextView totals = findViewById(R.id.tvTotal);
        String totalsText ="$ \t"+ Order.sizePrice+"\n\n";
        String detailsText = "Pizza size: "+ Order.PizzaSize +"\n" + "Toppings: " + "\n";

        for(Topping topping : Order.Toppings){
            detailsText += "\t -" + topping.topping_Name+"\n";
            totalsText += "$ \t" + topping.price+"\n";
        }

        Order.calculateTotal();
        detailsText+= "\nGrand Total:";
        totalsText  += "\n$ \t" + Order.grand_total+"\n";
        details.setText(detailsText);
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

    public String format_for_receipt(OrderDetails Order, Customer Customer) {
        String order_details = "Pizza Size: "+ Order.PizzaSize+ "\t\t  $ \t "+Order.sizePrice +"\n\n"+
                                Order.Toppings.size()+" Toppings:\n";
        for (Topping topping :  Order.Toppings)
        {
            order_details += "\t "+ topping.topping_Name+"\t\t  $ \t"+ topping.price+"\n";
        }
        order_details += "\nGrand Total: \t\t $"+ Order.grand_total+ "\n\n";

        String cust_details = "Customer Name: " + Customer.name +"\n\n" + "Phone Number: " + Customer.phone_number + "\n\n" + "Email: " + Customer.email+ "\n\n" + "Address: " + Customer.address+"\n";

        return order_details + cust_details;
    }

    public void save_writeReceipt(View view) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("receipt.txt", this.MODE_PRIVATE));
            outputStreamWriter.write(format_for_receipt(Order,Customer));
//            outputStreamWriter.write("Hello World");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public void newOrder(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
