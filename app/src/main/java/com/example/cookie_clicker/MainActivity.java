package com.example.cookie_clicker;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Application Tag";
    private final int GRANNY_COST = 200;
    private final int FARM_COST = 500;
    private final int FACTORY_COST = 1000;
    private final int X2_COST = 25;
    private final int X4_COST = 50;
    private final int X8_COST = 75;

    private final int GOAL_TO_WIN = 1000000;


    Boolean grannyIsLocked = true;
    Boolean farmIsLocked = true;
    Boolean factoryIsLocked = true;

    // X2, X4, X8 buttons can be enabled only once. These variables keep track of that
    Boolean x2HaveBeenBought = false;
    Boolean x4HaveBeenBought = false;
    Boolean x8HaveBeenBought = false;


    ImageButton cookieButton;
    public int cookieCounter = 0, multiply = 1;
    Button ResetButton;
    Button btnOne, btnTwo, btnThree;
    ImageButton grannyButton, farmButton,factoryButton;

    TextView descText, statsGrannies, statsFarms, statsFactories;
    int numOfGrannies = 0;
    int numOfFarms = 0;
    int numOfFactories = 0;

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


        // Initialize special features buttons
        grannyButton = findViewById(R.id.grannyButton);
        farmButton = findViewById(R.id.farmButton);
        factoryButton = findViewById(R.id.factoryButton);

        // Initially deactivate all special features buttons
        grannyButton.setEnabled(false);
        farmButton.setEnabled(false);
        factoryButton.setEnabled(false);

        // Set initial images of special features to dark. I.e. special features are "locked"
        grannyButton.setImageResource(android.R.color.transparent);
        grannyButton.setImageResource(R.drawable.locked_granny);
        farmButton.setImageResource(android.R.color.transparent);
        farmButton.setImageResource(R.drawable.locked_farm);
        factoryButton.setImageResource(android.R.color.transparent);
        factoryButton.setImageResource(R.drawable.locked_factory);


        // Statistics of how many grannies, farms, and factories have been bought
        statsGrannies = findViewById(R.id.statsGrannies);
        statsFarms = findViewById(R.id.statsFarms);
        statsFactories = findViewById(R.id.statsFactories);



        // The main "cookie" button
        cookieButton = (ImageButton) findViewById(R.id.cookieButton);
        cookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cookieCounter += multiply;
                text.setText(String.valueOf(cookieCounter) + " cookies");
                //System.out.println("Cookie was clicked\n");

                if(cookieCounter >= X2_COST && !x2HaveBeenBought) {
                    btnOne.setEnabled(true);
                    //btnOne.setBackgroundColor(Color.rgb(204,153,255));
                    btnOne.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }
                if(cookieCounter >= X4_COST && !x4HaveBeenBought) {
                    btnTwo.setEnabled(true);
                    //btnTwo.setBackgroundColor(Color.rgb(204,153,255));
                    btnTwo.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }
                if(cookieCounter >= X8_COST && !x8HaveBeenBought) {
                    btnThree.setEnabled(true);
                    btnThree.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                }


                if(cookieCounter >= GOAL_TO_WIN){
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
                if(cookieCounter >= X2_COST) {
                    multiply = 2;
                    cookieCounter -= X2_COST;
                    text.setText(String.valueOf(cookieCounter) + " cookies");

                    btnOne.setEnabled(false);
                    btnOne.getBackground().clearColorFilter();
                    x2HaveBeenBought = true;
                }
            }
        });


        btnTwo.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                System.out.println("Button was clicked!");
                if(cookieCounter >= X4_COST) {
                    multiply = 4;
                    cookieCounter -= X4_COST;
                    text.setText(cookieCounter + " cookies");


                    btnOne.setEnabled(false);
                    btnTwo.setEnabled(false);
                    btnOne.getBackground().clearColorFilter();
                    btnTwo.getBackground().clearColorFilter();

                    x2HaveBeenBought = true;
                    x4HaveBeenBought = true;
                }
            }
        });


        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cookieCounter >= X8_COST) {
                    multiply = 8;
                    cookieCounter -= X8_COST;
                    text.setText(cookieCounter + " cookies");


                    x2HaveBeenBought = true;
                    x4HaveBeenBought = true;
                    x8HaveBeenBought = true;

                    btnOne.getBackground().clearColorFilter();
                    btnTwo.getBackground().clearColorFilter();
                    btnThree.getBackground().clearColorFilter();

                    btnTwo.setEnabled(false);
                    btnOne.setEnabled(false);
                    btnThree.setEnabled(false);

                }
            }
        });

        grannyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieCounter -= GRANNY_COST;
                numOfGrannies++;
                statsGrannies.setText(numOfGrannies + " Grannies");
                text.setText(cookieCounter + " cookies");

                if(grannyButton.isEnabled() && cookieCounter < GRANNY_COST){
                    lockFeature(grannyButton, R.drawable.locked_granny);
                    lockFeature(farmButton, R.drawable.locked_farm);
                    lockFeature(factoryButton, R.drawable.locked_factory);
                    grannyIsLocked = true;
                    farmIsLocked = true;
                    factoryIsLocked = true;
                }
            }
        });

        farmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieCounter -= FARM_COST;
                numOfFarms++;
                statsFarms.setText(numOfFarms + " Farms");
                text.setText(cookieCounter + " cookies");

                if(farmButton.isEnabled() && cookieCounter < FARM_COST){
                    lockFeature(farmButton, R.drawable.locked_farm);
                    lockFeature(factoryButton, R.drawable.locked_factory);
                    farmIsLocked = true;
                    factoryIsLocked = true;
                }
            }
        });

        factoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieCounter -= FACTORY_COST;
                numOfFactories++;
                statsFactories.setText(numOfFactories + " Factories");
                text.setText(cookieCounter + " cookies");

                if(factoryButton.isEnabled() && cookieCounter < FACTORY_COST){
                    lockFeature(factoryButton, R.drawable.locked_factory);
                    factoryIsLocked = true;
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


                numOfFactories = 0;
                numOfFarms = 0;
                numOfGrannies = 0;

                x2HaveBeenBought = false;
                x4HaveBeenBought = false;
                x8HaveBeenBought = false;

                statsGrannies.setText("0 Grannies");
                statsFactories.setText("0 Factories");
                statsFarms.setText("0 Farms");
            }
        });


        // Thread operations
        final Runnable updater = new Runnable() {
            public void run() {

                // Unlock Granny if enough cookies. Lock it, if not enough cookies
                if(cookieCounter >= GRANNY_COST && grannyIsLocked){
                    unlockFeature(grannyButton, R.drawable.granny);
                    grannyIsLocked = false;
                } else if(cookieCounter < GRANNY_COST && !grannyIsLocked){
                    lockFeature(grannyButton, R.drawable.locked_granny);
                    grannyIsLocked = true;
                }

                // Unlock Farm if enough cookies. Lock it, if not enough cookies
                if(cookieCounter >= FARM_COST && farmIsLocked){
                    unlockFeature(farmButton, R.drawable.farm);
                    farmIsLocked = false;
                } else if(cookieCounter < FARM_COST && !farmIsLocked){
                    lockFeature(farmButton, R.drawable.locked_farm);
                    farmIsLocked = true;
                }

                // Unlock Factory if enough cookies. Lock it, if not enough cookies
                if(cookieCounter >= FACTORY_COST && factoryIsLocked){
                    unlockFeature(factoryButton, R.drawable.factory_and_warehouse_sets_illustration_in_sketch_design_6825474);
                    factoryIsLocked = false;
                } else if(cookieCounter < FACTORY_COST && !factoryIsLocked){
                    lockFeature(factoryButton, R.drawable.locked_factory);
                    factoryIsLocked = true;
                }




                // Update the counter and its UI text
                cookieCounter += 2 * numOfGrannies + 10 * numOfFarms + 50 * numOfFactories;
                text.setText(String.valueOf(cookieCounter) + " cookies");


                // Do some manipulations with X2 button only if it hasn't been bought earlier
                if (!x2HaveBeenBought) {
                    // Unlock X2 button if enough cookies. Lock it, if not enough cookies
                    if (!btnOne.isEnabled() && cookieCounter >= X2_COST) {
                        btnOne.setEnabled(true);
                        btnOne.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                    } else if (btnOne.isEnabled() && cookieCounter < X2_COST) {
                        btnOne.setEnabled(false);
                        btnOne.getBackground().clearColorFilter();
                    }
                }

                // Do some manipulations with X4 button only if it hasn't been bought earlier
                if (!x4HaveBeenBought) {
                    // Unlock X2 button if enough cookies. Lock it, if not enough cookies
                    if (!btnTwo.isEnabled() && cookieCounter >= X4_COST) {
                        btnTwo.setEnabled(true);
                        btnTwo.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                    } else if (btnTwo.isEnabled() && cookieCounter < X4_COST) {
                        btnTwo.setEnabled(false);
                        btnTwo.getBackground().clearColorFilter();
                    }
                }

                // Do some manipulations with X8 button only if it hasn't been bought earlier
                if (!x8HaveBeenBought) {
                    // Unlock X2 button if enough cookies. Lock it, if not enough cookies
                    if (!btnThree.isEnabled() && cookieCounter >= X8_COST) {
                        btnThree.setEnabled(true);
                        btnThree.getBackground().setColorFilter(0xFFCC99FF, PorterDuff.Mode.MULTIPLY);
                    } else if (btnThree.isEnabled() && cookieCounter < X8_COST) {
                        btnThree.setEnabled(false);
                        btnThree.getBackground().clearColorFilter();
                    }
                }


                if(cookieCounter >= GOAL_TO_WIN){
                    cookieButton.setImageResource(android.R.color.transparent);
                    cookieButton.setImageResource(R.drawable.congrats);
                    descText.setText(getResources().getString(R.string.win));
                    cookieButton.setEnabled(false);

                    numOfFactories = 0;
                    numOfFarms = 0;
                    numOfGrannies = 0;

                    grannyButton.setEnabled(false);
                    factoryButton.setEnabled(false);
                    farmButton.setEnabled(false);
                    btnOne.setEnabled(false);
                    btnTwo.setEnabled(false);
                    btnThree.setEnabled(false);
                }
            }
        };

        Thread backgroundWorker = new Thread() {
            public void run() {

                while (true) {
                    runOnUiThread(updater);

                    try {


                        Log.d(TAG, "local Thread sleeping");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "local Thread error", e);
                    }

                }
            }
        };

       backgroundWorker.start();


    }

    public void unlockFeature(ImageButton imb, int imageDrawableId){
        imb.setEnabled(true);
        imb.setImageResource(imageDrawableId);
    }

    public void lockFeature(ImageButton imb, int imageDrawableId){
        imb.setEnabled(false);
        imb.setImageResource(imageDrawableId);
    }






}
