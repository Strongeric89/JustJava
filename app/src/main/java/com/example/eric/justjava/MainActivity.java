package com.example.eric.justjava;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button = null;
    private TextView quantity = null;
    private TextView price = null;
    private TextView orderSummary = null;
    private Button buttonAdd = null;
    private Button buttonSub = null;
    private CheckBox whippedCream = null;
    private CheckBox chocolate = null;
    private EditText cusname = null;


    public int num = 0;
    public double total = 0.00;
    public double pricePerItem = 3.50;

    public String greeting = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        quantity = (TextView) findViewById(R.id.quantity);
        price = (TextView) findViewById(R.id.price);
        orderSummary = (TextView) findViewById(R.id.orderSumary);
        buttonAdd = (Button) findViewById(R.id.addButton);
        buttonSub = (Button) findViewById(R.id.subButton);
        whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        chocolate = (CheckBox) findViewById(R.id.chocolate);
        cusname = (EditText) findViewById(R.id.name);

    }

    public void addQuantity(View view) {


        num++;

        total = total + pricePerItem;
        price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));
        price.setTextColor(Color.GREEN);
        Log.d("eric", "" + total);
        quantity.setText("" + num);
        quantity.setTextColor(Color.RED);
        orderSummary.setText("Price");


    }

    public void subQuantity(View view) {

        num--;
        orderSummary.setText("Price");

        if (num < 0) {
            num = 0;
            total = 0.00;



            price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));
            Toast.makeText(MainActivity.this, "you can only select > 0 Quantity",
                    Toast.LENGTH_LONG).show();

        } else {

            total = total - pricePerItem;
            price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));
            price.setTextColor(Color.RED);
        }


        Log.d("eric", "" + total);
        quantity.setText("" + num);

    }

    public void orderSubmit(View view) {
        if (num == 0) {
            Toast.makeText(MainActivity.this, "you have not yet selected any quantity",
                    Toast.LENGTH_LONG).show();
        } else {


            String name = "Name ";

            name += cusname.getText().toString();
            String orderQuant = "Quantity: " + num;
            String whipped = "";
            String choc = "";

            if (whippedCream.isChecked()) {
                whipped = "\nWhipped Cream: " + whippedCream.isChecked();
                //total += 0.50;
            } else {
                whipped = "\nWhipped Cream: " + whippedCream.isChecked();
            }

            if (chocolate.isChecked()) {
                choc = "\nChocolate: " + chocolate.isChecked();
                //total += 0.60;
            } else {
                choc = "\nChocolate: " + chocolate.isChecked();
            }


            greeting = "" + name + "\n" + orderQuant + "\nTotal:" + NumberFormat.getCurrencyInstance(Locale.UK).format(total) + whipped + choc + "\nThank you!!!";


            orderSummary.setText("Order Summary");

            Toast.makeText(MainActivity.this, greeting,Toast.LENGTH_LONG).show();

        }

    }

    public void cream(View view){

        boolean addCream = whippedCream.isChecked();
        if(addCream){
            total += 0.50;
            price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));

        }else{
            total -= 0.50;
            price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));
        }

    }

    public void chocolate(View view){
        boolean addChoc = chocolate.isChecked();
        if(addChoc){
            total += 0.60;
            price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));

        }else{
            total -= 0.60;
            price.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(total));
        }

    }

    public void send(View view){
        String [] emails = {"strong.erik@gmail.com"};
        composeEmail(emails,"Just Java Coffee Order", greeting);

    }

    public void composeEmail(String[] addresses, String subject, String details) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, details);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
