package com.example.mockform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables and constants
    final String NIP = "No Input Provided";

    String name ="";
    String address = "";
    String city = "";
    String state = "";
    String zipcode = "";
    String gender = "";
    String toggleButton = "";

    //Widgets
    EditText editTextName;
    EditText editTextAddress;
    EditText editTextCity;
    Spinner spinnerState;
    EditText editTextZipCode;
    RadioGroup radioGroup;
    RadioButton radioButtonFemale;
    RadioButton radioButtonMale;
    RadioButton radioButtonNoAnswer;
    CheckBox checkBoxMorning;
    CheckBox checkBoxAfternoon;
    CheckBox checkBoxEvening;
    Switch switchSettings;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextCity = findViewById(R.id.editTextCity);
        spinnerState = findViewById(R.id.spinnerState);
        editTextZipCode = findViewById(R.id.editTextZipCode);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonNoAnswer = findViewById(R.id.radioButtonNoAnswer);
        checkBoxAfternoon = findViewById(R.id.checkBoxAfternoon);
        checkBoxEvening = findViewById(R.id.checkBoxEvening);
        checkBoxMorning = findViewById(R.id.checkBoxMorning);
        switchSettings = findViewById(R.id.switchSettings);
        imageButton = findViewById(R.id.imageButton);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //EditTexts
                name = (editTextName.getText().length() == 0)
                        ? NIP : editTextName.getText().toString();
                address = (editTextAddress.getText().length() == 0)
                        ? NIP : editTextAddress.getText().toString();
                city = (editTextCity.getText().length() == 0)
                        ? NIP : editTextCity.getText().toString();
                state = (spinnerState.getSelectedItem().toString().equals("Choose A State"))
                        ? NIP : spinnerState.getSelectedItem().toString();
                zipcode = (editTextZipCode.getText().length() == 0)
                        ? NIP : editTextZipCode.getText().toString();

                //Radios
                int selectedRadio = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedRadio);

                gender = (radioButton == null) ? NIP : radioButton.getText().toString();


                //Checkboxes
                String shiftStr = NIP;

                if(checkBoxMorning.isChecked())
                {
                    shiftStr = "Morning";
                }
                if(checkBoxAfternoon.isChecked())
                {
                    shiftStr += " Afternoon";
                }
                if(checkBoxEvening.isChecked())
                {
                    shiftStr += " Evening";
                }


                //Switch/ToggleButton
                toggleButton = (switchSettings.isChecked()) ? "ON" : "OFF";

                //get results
                String result = "NAME: "     + name + "\n";
                       result +="ADDRESS: "  + address + "\n";
                       result +="CITY: "     + city + "\n";
                       result +="STATE: "    + state + "\n";
                       result +="ZIPCODE: "  + zipcode + "\n";
                       result +="GENDER: "   + gender + "\n";
                       result +="SHIFT: "    + shiftStr + "\n";
                       result +="SETTINGS: " + toggleButton + "\n";


                Toast.makeText(MainActivity.this,
                        result, Toast.LENGTH_LONG).show();


            }
        });


    }
}
