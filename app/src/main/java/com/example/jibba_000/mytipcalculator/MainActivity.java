package com.example.jibba_000.mytipcalculator;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.icu.text.NumberFormat.*;

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat currencyFormat = getCurrencyInstance();

    private static final NumberFormat percentFormat = getPercentInstance();


    private double billAmount = 0.00; //amount edited by the user
    private double percent = 0.15; // percent of tips
    private TextView percentView; // shows tip percent
    private TextView amountView; // shows the amount without tip
    private TextView totalLabelView; // shows total amount
    private TextView tipLabelView; // shows the calculated tip amount

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //call superclass version
        setContentView(R.layout.activity_main); // inflate the gui

        //get references to programmatically manipulated textviews
        amountView = (TextView) findViewById(R.id.amountView);
        percentView = (TextView) findViewById(R.id.percentView);
        tipLabelView = (TextView) findViewById(R.id.tipLabelView);
        totalLabelView = (TextView) findViewById(R.id.totalLabelView);
        tipLabelView.setText(currencyFormat.format(0)); //set text to 0
        totalLabelView.setText(currencyFormat.format(0)); //set text to 0

        //set edit amounts textWatcher
        EditText amountEditText = (EditText) findViewById(R.id.editTextAmount);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        // set seekbar
        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.seekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }
    //calculate and display tip and total amounts
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calculate(){
        //format percent and display in percentView
        percentView.setText(percentFormat.format(percent));

        //calculate the tip and total
        double tip = billAmount * percent;
        double total = billAmount + tip;

        //display tip and total formatted as currency
        tipLabelView.setText(currencyFormat.format(tip));
        totalLabelView.setText(currencyFormat.format(total));
    }
    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        //update percent, then call calculate
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0; //set percent based on progress
            calculate(); //calculate and display tip and total
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    //listener object for the edittexts text-changed events
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        //called when the user modifies the bill amount
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{ //get bill amount and display currency formatted value
                billAmount = Double.parseDouble(s.toString()) / 100.0;
                amountView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e){ //if s is empty or non-numeric
                amountView.setText("");
                billAmount = 0.0;
            }
            calculate(); //update the tip and total textviews
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };
}
