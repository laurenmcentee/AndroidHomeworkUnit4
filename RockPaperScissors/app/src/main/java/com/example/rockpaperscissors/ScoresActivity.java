package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoresActivity extends AppCompatActivity {

    int ties = 0;
    int userWins = 0;
    int programWins = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TextView textViewUserWins;
        TextView textViewProgramWins;
        TextView textViewTies;
        Button buttonBack;

        textViewUserWins = findViewById(R.id.textViewUserWins);
        textViewProgramWins = findViewById(R.id.textViewProgramWins);
        textViewTies = findViewById(R.id.textViewTies);
        buttonBack = findViewById(R.id.buttonBack);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
        {
            if(extras.containsKey("ties"))
            {
                ties = extras.getInt("ties", 0);
            }
            if(extras.containsKey("userWins"))
            {
                userWins = extras.getInt("userWins", 0);
            }
            if(extras.containsKey("programWins"))
            {
                programWins = extras.getInt("programWins", 0);
            }

            String pw = Integer.toString(programWins);
            String uw = Integer.toString(userWins);
            String t = Integer.toString(ties);

            textViewProgramWins.setText(pw);
            textViewUserWins.setText(uw);
            textViewTies.setText(t);
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




    }
}
