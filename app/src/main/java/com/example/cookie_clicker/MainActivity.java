package com.example.cookie_clicker;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton cookieButton;
    public int cookieCounter = 0, multiply = 1;
    Button ResetButton;
    Button btnOne, btnTwo, btnThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView text = findViewById(R.id.textView);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        ResetButton = findViewById(R.id.Reset);





        cookieButton = (ImageButton) findViewById(R.id.cookieButton);
        cookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieCounter += multiply;
                text.setText(String.valueOf(cookieCounter) + " cookies");
                //System.out.println("Cookie was clicked\n");

                if(cookieCounter >= 50) {
                    btnOne.setEnabled(true);
                    //btnOne.setBackgroundColor(Color.rgb(204,153,255));
                    btnOne.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }
                if(cookieCounter >= 100) {
                    btnTwo.setEnabled(true);
                    //btnTwo.setBackgroundColor(Color.rgb(204,153,255));
                    btnTwo.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }
                if(cookieCounter >= 200) {
                    btnThree.setEnabled(true);
                    btnThree.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }

            }
        });



        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cookieCounter >= 50) {
                    multiply = 2;
                    cookieCounter -= 50;
                    text.setText(String.valueOf(cookieCounter) + " cookies");
                }
            }
        });


        btnTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Button was clicked!");
                if(cookieCounter >= 100) {
                    multiply = 4;
                    cookieCounter -= 100;
                    text.setText(String.valueOf(cookieCounter) + " cookies");
                }
            }
        });


        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cookieCounter >= 200) {
                    multiply = 8;
                    cookieCounter -= 200;
                    text.setText(String.valueOf(cookieCounter) + " cookies");
                }
            }
        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieCounter = 0;
                multiply = 1;
                btnOne.setEnabled(false);
                btnTwo.setEnabled(false);
                btnThree.setEnabled(false);
                btnOne.getBackground().clearColorFilter();
                btnTwo.getBackground().clearColorFilter();
                btnThree.getBackground().clearColorFilter();
                text.setText(String.valueOf(cookieCounter) + " cookies");
            }
        });


    }




}
