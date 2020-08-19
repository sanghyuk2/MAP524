package com.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    Button donate;
    RadioButton paypal_payment;
    RadioButton credit_payment;

    CheckBox whatsup_check;
    CheckBox messenger_check;
    CheckBox messages_check;

    EditText amount;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(context: this);

        donate = (Button) findViewById(R.id.donate_id);

        paypal_payment = (RadioButton)findViewById(R.id.paypal_btn);
        credit_payment = (RadioButton)findViewById(R.id.credit_btn);
        whatsup_check = (CheckBox)findViewById(R.id.wup_check);
        messenger_check = (CheckBox)findViewById(R.id.messenger_check);
        messages_check = (CheckBox)findViewById(R.id.messages_check);
        amount = (EditText) findViewById(R.id.amount);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidity()){
                    Toast.makeText(getApplicationContext(), R.string.messege, Toast.LENGTH_SHORT).show();

                    String payment, sharing = " and sharing the link via ";

                    if (paypal_payment.isChecked())
                        payment = "PayPal";
                    else
                        payment = "Credit card";

                    Boolean shared = false;

                    if (whatsup_check.isChecked()) {
                        sharing += "What's up";
                        shared = true;
                    }
                    if (messenger_check.isChecked()) {
                        sharing += " massenger ";
                        shared = true;
                    }
                    if (messages_check.isChecked()) {
                        sharing += " messages";
                        shared = true;
                    }
                    if (!shared)
                        sharing = "";

                    int a = Integer.parseInt(amount.getText().toString());

                    builder.setMessage("Thanks for your " + payment + " payment with amount " + a + sharing)
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });


                    AlertDialog alert = builder.create();
                    alert.setTitle("Thank You");
                    alert.show();
                }//if
                else {
                    Toast.makeText(getApplicationContext(), R.string.errorMessage, Toast.LENGTH_SHORT).show();
                }
        }


        });
    }

    public boolean checkValidity(){
        boolean valid;

        if (!credit_payment.isChecked() && !paypal_payment.isChecked()){
           return valid = false;

        }else if (amount.getText().toString().isEmpty()) {
            return valid = false;
        }

        return valid = true;
    }
}
