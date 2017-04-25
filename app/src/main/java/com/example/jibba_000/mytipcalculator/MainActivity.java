package com.example.jibba_000.mytipcalculator;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.icu.text.NumberFormat.*;

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat currencyFormat = getCurrencyInstance();

    private static final NumberFormat percentFormat = getPercentInstance();


    private double billAmount = 0.0; //amount edited by the user
    private double percent = .15; // percent of tips
    private TextView percentView; // shows tip percent
    private TextView amountView; // shows the amount without tip
    private TextView totalLabelView; // shows total amount
    private TextView tipLabelView; // shows the calculated tip amount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
