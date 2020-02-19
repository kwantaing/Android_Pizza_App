package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        OrderDetails Order = (OrderDetails) getIntent().getSerializableExtra("ORDER");
        show_orderDetails(Order);
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
    public OrderDetails updateOrderforSummary(OrderDetails Order){
        Order.calculateTotal();
        return Order;
    }

    public void submitOrder(View view) {
        EditText cust_name = findViewById(R.id.cust_name);
        EditText cust_phone = findViewById(R.id.cust_phone);
        EditText cust_email = findViewById(R.id.cust_email);
        EditText cust_address = findViewById(R.id.cust_address);

        if(!check_cust_fields(cust_name,cust_phone,cust_email,cust_address)){
            onRestart();
        }else{
            Customer customer = new Customer(
                                        cust_name.getText().toString(),
                                        cust_phone.getText().toString(),
                                        cust_email.getText().toString(),
                                        cust_address.getText().toString()
                                );

            Intent intent = new Intent(this,OrderSummary.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CUSTOMER",customer);
            OrderDetails Order = (OrderDetails) getIntent().getSerializableExtra("ORDER");
            show_orderDetails(Order);
            bundle.putSerializable("ORDER",Order);
            intent.putExtras(bundle);
            startActivity(intent);
        }


    }

    public boolean check_cust_fields(EditText cust_name, EditText cust_phone, EditText cust_email, EditText cust_address){
        if(cust_name.length() <2 || cust_phone.length() !=10 ||cust_email.length()<5 ||cust_address.length() <5) {
            Toast.makeText(this,"Please fill in all fields appropriately",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
