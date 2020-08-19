
package com.example.week2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText firstNumber;
    EditText secondNumber;
    Button plus_but;
    Button minuse_but;
    Button times_but;
    Button divid_but;
    TextView resultText;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = (EditText) findViewById(R.id.firstNum);
        secondNumber = (EditText) findViewById(R.id.secondNum);

        resultText = (TextView) findViewById(R.id.result_text);

        plus_but = (Button) findViewById(R.id.add_butt);
         minuse_but = (Button) findViewById(R.id.minus_butt);
         times_but = (Button) findViewById(R.id.times_butt);
         divid_but  = (Button) findViewById(R.id.divid_butt);

       plus_but.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               result = Integer.parseInt(firstNumber.getText().toString()) + Integer.parseInt(secondNumber.getText().toString());
               resultText.setText(String.valueOf(result));

           }
       });
        minuse_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = Integer.parseInt(firstNumber.getText().toString()) - Integer.parseInt(secondNumber.getText().toString());
                resultText.setText(String.valueOf(result));

            }
        });

        times_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((firstNumber.getText() != null) && (secondNumber.getText() != null)) {
                    result = Integer.parseInt(firstNumber.getText().toString()) * Integer.parseInt(secondNumber.getText().toString());
                    resultText.setText(String.valueOf(result));
                }

            }
        });
        divid_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = Integer.parseInt( firstNumber.getText().toString()) / Integer.parseInt(secondNumber.getText().toString());
                resultText.setText(String.valueOf(result));
            }
        });




    }

}



