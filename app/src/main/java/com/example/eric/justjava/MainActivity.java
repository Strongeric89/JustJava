package com.example.eric.justjava;

import java.text.NumberFormat;
import java.util.Locale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button = null;
    private TextView quantity = null;
    private TextView price = null;
    private Button buttonAdd = null;
    private Button buttonSub = null;

    public int num = 0;
    public double total = 0.00;
    public double pricePerItem = 3.50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        quantity = (TextView) findViewById(R.id.quantity);
        price = (TextView) findViewById(R.id.price);
        buttonAdd = (Button) findViewById(R.id.addButton);
        buttonSub = (Button) findViewById(R.id.subButton);

    }

    public void addQuantity(View view){

        num ++;
        total = total + pricePerItem;
        price.setText("Total " + NumberFormat.getCurrencyInstance(Locale.UK).format(total) + "\nThank you!");
        Log.d("eric","" + total);
        quantity.setText("" + num);

    }

    public void subQuantity(View view){

        num --;

        if(num < 0){
            num = 0;
            total = 0.00;
            price.setText("Total " + NumberFormat.getCurrencyInstance(Locale.UK).format(total));
            Toast.makeText(MainActivity.this, "you can only select > 0 Quantity",
                    Toast.LENGTH_LONG).show();

        }else{
            total = total - pricePerItem;
            price.setText("Total " + NumberFormat.getCurrencyInstance(Locale.UK).format(total));
        }


        Log.d("eric","" + total);
        quantity.setText("" + num);

    }

    public void orderSubmit(View view){
        if(num == 0){
            Toast.makeText(MainActivity.this, "you have not yet selected any quantity",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "your order is " + num + " Coffees. Total " + NumberFormat.getCurrencyInstance(Locale.UK).format(total),
                    Toast.LENGTH_LONG).show();
        }


    }

}
