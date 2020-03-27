package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    final String NO = "NO";
    final String NEG = "Negative";
    final String IP = "Input provided for amount to convert";
    final String NC = "No converting rate to self rate";

    //currency symbols
    final String DOLLAR = "\u0024";
    final String EURO = "\u20ac";
    final String POUND = "\u00a3";
    final String FRANC = "\u20A3";
    final String YEN = "\u00A5";

    //conversion constants
    // I provided US dollars conversion rates

    final double USD_TO_EEURO = 0.92;
    final double USD_TO_BPOUND = 0.77;
    final double USD_TO_SFRANC = 0.98;
    final double USD_TO_AUSTD = 1.49;
    final double USD_TO_CANAD = 1.33;
    final double USD_TO_NEWZD = 1.55;
    final double USD_TO_JYEN = 109.79;

    final double EEURO_TO_USD = 1.12;
    final double EEURO_TO_BPOUND = 0.87;
    final double EEURO_TO_SFRANC = 1.06;
    final double EEURO_TO_AUSTD = 1.70;
    final double EEURO_TO_CANAD = 1.50;
    final double EEURO_TO_NEWZD = 1.78;
    final double EEURO_TO_JYEN = 119.23;

    final double BPOUND_TO_USD = 1.29;
    final double BPOUND_TO_EEURO = 1.15;
    final double BPOUND_TO_SFRRANC = 1.23;
    final double BPOUND_TO_AUSTD = 1.96;
    final double BPOUND_TO_CANAD = 1.74;
    final double BPOUND_TO_NEWZD = 2.05;
    final double BPOUND_TO_JYEN = 137.65;

    final double SFRANC_TO_USD = 1.05;
    final double SFRANC_TO_EEURO = 0.94;
    final double SFRANC_TO_BPOUND = 0.81;
    final double SFRANC_TO_AUSTD = 1.60;
    final double SFRANC_TO_CANAD = 1.42;
    final double SFRANC_TO_NEWZD = 1.67;
    final double SFRANC_TO_JYEN = 112.17;

    final double AUSTD_TO_USD = 0.61;
    final double AUSTD_TO_EEURO = 0.55;
    final double AUSTD_TO_BPOUND = 0.49;
    final double AUSTD_TO_SFRANC = 0.59;
    final double AUSTD_TO_NEWZD = 1.02;
    final double AUSTD_TO_JYEN = 66.08;
    final double AUSTD_TO_CANAD = 0.86;

    final double CANAD_TO_USD = 0.74;
    final double CANAD_TO_EEURO = 0.67;
    final double CANAD_TO_BPOUND = 0.58;
    final double CANAD_TO_SFRANC = 0.71;
    final double CANAD_TO_AUSTD = 1.13;
    final double CANAD_TO_NEWZD = 1.18;
    final double CANAD_TO_JYEN = 79.27;

    final double NEWZD_TO_USD = 0.63;
    final double NEWZD_TO_EEURO = 0.56;
    final double NEWZD_TO_BPOUND = 0.49;
    final double NEWZD_TO_SFRANC = 0.60;
    final double NEWZD_TO_CANAD = 0.85;
    final double NEWZD_TO_JYEN = 67.01;
    final double NEWZD_TO_AUSTD = 0.95;

    final double JYEN_TO_USD = 0.0094;
    final double JYEN_TO_EEURO = 0.0084;
    final double JYEN_TO_BPOUND = 0.0073;
    final double JYEN_TO_SFRANC = 0.0089;
    final double JYEN_TO_AUSTD = 0.0014;
    final double JYEN_TO_CANAD = 0.0013;
    final double JYEN_TO_NEWZD = 0.0015;

    //widgets
    EditText editTextAmount;
    Spinner spinnerFrom;
    Spinner spinnerTo;
    Button buttonConvert;
    Toast t;

    boolean keepGoing;
    double inputtedAmount;
    double conversionRate;
    double convertedAmount;
    String convertFrom;
    String convertTo;
    int from;
    int to;
    DecimalFormat df = new DecimalFormat("###,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonConvert = findViewById(R.id.buttonConvert);

        //initialize variables
        keepGoing = true;
        inputtedAmount = 0.0;
        conversionRate = 0.0;
        convertedAmount = 0.0;
        from = -1;
        to = -1;
        convertFrom = "";
        convertTo = "";

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                 this, R.array.currencies_array,
                                  android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set adapter to spinner
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                keepGoing = checkForNoInput();

                if (keepGoing) {
                    doTheConversion();
                }
            }
        });
    }

    private boolean checkForNoInput() {
        //check for no input
        if (editTextAmount.getText().toString().isEmpty()) {
            t = Toast.makeText(getApplicationContext(),
                    NO + IP, Toast.LENGTH_LONG);
            t.show();
            return false;
        }
        //amount provided
        else {
            inputtedAmount = Double.valueOf(editTextAmount.getText().toString());
            //first check for negative input
            if (inputtedAmount < 0) {
                t = Toast.makeText(getApplicationContext(),
                        NEG + IP,
                        Toast.LENGTH_LONG);
                t.show();
                return false;
            }
            return true;
        }
    }

        private void doTheConversion()
        {
            from = spinnerFrom.getSelectedItemPosition();
            to = spinnerTo.getSelectedItemPosition();

            switch (from)
            {
                case 0:
                    convertFrom = " US Dollars ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = USD_TO_EEURO;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = USD_TO_BPOUND;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = USD_TO_SFRANC;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = USD_TO_AUSTD;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = USD_TO_CANAD;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = USD_TO_NEWZD;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = USD_TO_JYEN;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 1:
                    convertFrom = " European Euros ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = EEURO_TO_USD;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = EEURO_TO_AUSTD;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = EEURO_TO_BPOUND;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = EEURO_TO_NEWZD;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = EEURO_TO_SFRANC;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = EEURO_TO_JYEN;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = EEURO_TO_CANAD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 2:
                    convertFrom = " British Pounds ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = BPOUND_TO_AUSTD;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = BPOUND_TO_CANAD;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = BPOUND_TO_EEURO;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = BPOUND_TO_JYEN;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = BPOUND_TO_NEWZD;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = BPOUND_TO_SFRRANC;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = BPOUND_TO_USD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 3:
                    convertFrom = " Swiss Francs ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = SFRANC_TO_AUSTD;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = SFRANC_TO_BPOUND;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = SFRANC_TO_CANAD;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = SFRANC_TO_EEURO;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = SFRANC_TO_JYEN;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = SFRANC_TO_NEWZD;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = SFRANC_TO_USD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 4:
                    convertFrom = " Australian Dollars ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = AUSTD_TO_BPOUND;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = AUSTD_TO_CANAD;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = AUSTD_TO_EEURO;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = AUSTD_TO_JYEN;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = AUSTD_TO_NEWZD;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = AUSTD_TO_SFRANC;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = AUSTD_TO_USD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 5:
                    convertFrom = " Canadian Dollars ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = CANAD_TO_AUSTD;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = CANAD_TO_BPOUND;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = CANAD_TO_EEURO;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = CANAD_TO_JYEN;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = CANAD_TO_SFRANC;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = CANAD_TO_USD;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = CANAD_TO_NEWZD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 6:
                    convertFrom = " New Zealand Dollars ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = NEWZD_TO_AUSTD;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = NEWZD_TO_BPOUND;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = NEWZD_TO_CANAD;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = NEWZD_TO_EEURO;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = NEWZD_TO_JYEN;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = NEWZD_TO_SFRANC;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = NEWZD_TO_USD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
                case 7:
                    convertFrom = " Japanese Yen ";
                    switch (to)
                    {
                        case 0:
                            conversionRate = 0;
                            t = Toast.makeText(getApplicationContext(),
                                    NC,
                                    Toast.LENGTH_LONG);
                            t.show();
                            break;

                        case 1:
                            conversionRate = JYEN_TO_AUSTD;
                            convertTo = toType(1);
                            break;
                        case 2:
                            conversionRate = JYEN_TO_BPOUND;
                            convertTo = toType(2);
                            break;
                        case 3:
                            conversionRate = JYEN_TO_CANAD;
                            convertTo = toType(3);
                            break;
                        case 4:
                            conversionRate = JYEN_TO_EEURO;
                            convertTo = toType(4);
                            break;
                        case 5:
                            conversionRate = JYEN_TO_NEWZD;
                            convertTo = toType(5);
                            break;
                        case 6:
                            conversionRate = JYEN_TO_SFRANC;
                            convertTo = toType(6);
                            break;
                        case 7:
                            conversionRate = JYEN_TO_USD;
                            convertTo = toType(7);
                            break;
                    }
                    break;
            }

            convertedAmount = inputtedAmount * conversionRate;
            t = Toast.makeText (getApplicationContext(),
                                df.format(inputtedAmount).toString() + convertFrom + " = " +
                                df.format(convertedAmount).toString() + convertTo,
                                        Toast.LENGTH_LONG);
            t.show();
        }

        private String toType(int fromType)
        {
            String retVal = "";
            switch (fromType)
            {
                case 0:
                    retVal = " US Dollars ";
                    break;
                case 1:
                    retVal = " European Euros ";
                    break;
                case 2:
                    retVal = " British Pounds ";
                    break;
                case 3:
                    retVal = " Swiss Francs ";
                    break;
                case 4:
                    retVal = " Australian Dollars ";
                    break;
                case 5:
                    retVal = " Canadian Dollars ";
                    break;
                case 6:
                    retVal = " New Zealand Dollars ";
                    break;
                case 7:
                    retVal = " Japanese Yen ";
                    break;
            }

            return retVal;
        }
    }

