package com.example.cookie_clicker;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
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
    Boolean flag1 = true;
    Boolean flag2 = true;
    Boolean flag3 = true;
    TextView descText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView text = findViewById(R.id.textView);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        ResetButton = findViewById(R.id.Reset);
        descText = findViewById(R.id.textView2);





        cookieButton = (ImageButton) findViewById(R.id.cookieButton);
        cookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                cookieCounter += multiply;
                text.setText(String.valueOf(cookieCounter) + " cookies");
                //System.out.println("Cookie was clicked\n");

                if(cookieCounter >= 50 && flag1) {
                    btnOne.setEnabled(true);
                    //btnOne.setBackgroundColor(Color.rgb(204,153,255));
                    btnOne.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }
                if(cookieCounter >= 100 && flag2) {
                    btnTwo.setEnabled(true);
                    //btnTwo.setBackgroundColor(Color.rgb(204,153,255));
                    btnTwo.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }
                if(cookieCounter >= 200 && flag3) {
                    btnThree.setEnabled(true);
                    btnThree.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }

                if(cookieCounter >= 500){
                    descText.setText(getResources().getString(R.string.half));
                }

                if(cookieCounter >= 1000){
                    cookieButton.setImageResource(android.R.color.transparent);
                    cookieButton.setImageResource(R.drawable.congrats);
                    descText.setText(getResources().getString(R.string.win));
                    cookieButton.setEnabled(false);
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
                    btnOne.setEnabled(false);
                    flag1 = false;
                }
            }
        });


        btnTwo.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                System.out.println("Button was clicked!");
                if(cookieCounter >= 100) {
                    multiply = 4;
                    cookieCounter -= 100;
                    text.setText(cookieCounter + " cookies");
                    btnTwo.setEnabled(false);
                    flag2 = false;
                    flag1 = false;

                    btnOne.setEnabled(false);
                }
            }
        });


        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cookieCounter >= 200) {
                    multiply = 8;
                    cookieCounter -= 200;
                    text.setText(cookieCounter + " cookies");
                    btnThree.setEnabled(false);
                    flag3 = false;
                    flag2 = false;
                    flag1 = false;

                    btnTwo.setEnabled(false);
                    btnOne.setEnabled(false);

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
                text.setText(cookieCounter + " cookies");

                cookieButton.setImageResource(android.R.color.transparent);
                cookieButton.setImageResource(R.drawable.cookie);
                cookieButton.setEnabled(true);
                descText.setText(getResources().getString(R.string.desc));
                flag1 = true;
                flag2 = true;
                flag3 = true;
            }
        });


    }




}
