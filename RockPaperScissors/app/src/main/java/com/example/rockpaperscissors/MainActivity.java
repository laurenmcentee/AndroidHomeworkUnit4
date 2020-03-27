package com.example.rockpaperscissors;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //widgets
    ImageButton imageButtonRock;
    ImageButton imageButtonPaper;
    ImageButton imageButtonScissors;
    Button buttonTotals;
    Button buttonNewGame;
    TextView textViewProgramChoice;
    TextView textViewUserChoice;
    TextView textViewWinner;
    //constants
    final String rock = "rock";
    final String paper = "paper";
    final String scissors = "scissors";
    final String startMessage = "Click New Game To Begin!";
    final String winMessage = " is the Winner. Click New game to Play Again.";
    //accumulators
    int programWins = 0;
    int userWins = 0;
    int ties = 0;
    // global variables
    String displayWinner = "";
    String programChoice = "";
    int number = 0;
    //objects
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonPaper = findViewById(R.id.imageButtonPaper);
        imageButtonRock = findViewById(R.id.imageButtonRock);
        imageButtonScissors = findViewById(R.id.imageButtonScissors);
        buttonTotals = findViewById(R.id.buttonTotals);
        buttonNewGame = findViewById(R.id.buttonNewGame);
        textViewProgramChoice = findViewById(R.id.textViewProgramChoice);
        textViewUserChoice = findViewById(R.id.textViewUserChoice);
        textViewWinner = findViewById(R.id.textViewWinner);

        disableButtons();

        Toast.makeText(MainActivity.this,
                startMessage, Toast.LENGTH_SHORT).show();


        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonScissors.setEnabled(true);
                imageButtonPaper.setEnabled(true);
                imageButtonRock.setEnabled(true);
                textViewProgramChoice.setText("-");
                textViewUserChoice.setText("-");
                textViewWinner.setText("-");
                programChoice = "";
                displayWinner = "";
                //generate a new random number between 1 and 3
                 number = (int)(Math.random() * 3 + 1);
            }
        });


        imageButtonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                programChoice = programChooses();
                displayWinner = findWinner("rock");
                 //set texts
                textViewProgramChoice.setText(programChoice);
                textViewUserChoice.setText(rock);
                textViewWinner.setText(displayWinner);
                disableButtons();

                Toast.makeText(MainActivity.this,
                       displayWinner + winMessage, Toast.LENGTH_LONG).show();
            }
        });

        imageButtonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                programChoice = programChooses();
                displayWinner = findWinner("paper");
                //set texts
                textViewProgramChoice.setText(programChoice);
                textViewUserChoice.setText(paper);
                textViewWinner.setText(displayWinner);
                disableButtons();

                Toast.makeText(MainActivity.this,
                        displayWinner + winMessage, Toast.LENGTH_LONG).show();
            }
        });

        imageButtonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                programChoice = programChooses();
                displayWinner = findWinner("scissors");
                //set texts
                textViewProgramChoice.setText(programChoice);
                textViewUserChoice.setText(scissors);
                textViewWinner.setText(displayWinner);
                disableButtons();

                Toast.makeText(MainActivity.this,
                        displayWinner + winMessage, Toast.LENGTH_LONG).show();
            }
        });

        buttonTotals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle extras = new Bundle();
                extras.putInt("ties", ties);
                extras.putInt("userWins", userWins);
                extras.putInt("programWins", programWins);
                Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        } // end of OnCreate

        public void disableButtons()
        {
            imageButtonScissors.setEnabled(false);
            imageButtonPaper.setEnabled(false);
            imageButtonRock.setEnabled(false);
        }

        public String programChooses()
        {
            number = (int)(Math.random() * 3 + 1);
            String result = "";

            if(number == 1)
            {
                result = "rock";
            }
            if(number == 2)
            {
                result = "paper";
            }
            if(number == 3)
            {
                result = "scissors";
            }

            return result;
        }

        public String findWinner(String n)
        {
            String winner = "";
            if(n.equals(programChoice))
            {
                winner = "Tie";
                ties++;
            }

            //user chooses rock
            if(n.contains("rock")) {
                if(programChoice.contains("scissors")) {
                    winner = "User";
                    userWins++;
                }
                if(programChoice.contains("paper")) {
                    winner = "Program";
                    programWins++;
                }
            }
            //user chooses scissors
            if(n.contains("scissors")) {
                if(programChoice.contains("paper")) {
                    winner = "User";
                    userWins++;
                }
                if(programChoice.contains("rock")) {
                    winner = "Program";
                    programWins++;
                }
            }
            //user chooses paper
            if(n.contains("paper")) {
                if(programChoice.contains("rock")) {
                    winner = "User";
                    userWins++;
                }
                if(programChoice.contains("scissors")) {
                    winner = "Program";
                    programWins++;
                }
            }
            return winner;
        }

    }

