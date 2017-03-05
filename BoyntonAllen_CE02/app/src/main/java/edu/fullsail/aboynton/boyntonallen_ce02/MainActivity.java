// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce02;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Generate Random class to create random numbers for each editText
    private Random randomNumber;

    // Initializing each editText
    private EditText[] editTexts;
    private int[] answerNums;
    private int[] userNumbers;

    // Initialize user's number of guesses as 4 and counting down as button is tapped
    private int totalGuesses = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set OnClick for submit button
        findViewById(R.id.submit_guess).setOnClickListener(listener);

        // Set views for each editText view
        editTexts = new EditText[]{
                (EditText) findViewById(R.id.number_one),
                (EditText) findViewById(R.id.number_two),
                (EditText) findViewById(R.id.number_three),
                (EditText) findViewById(R.id.number_four)
        };

        int answerNum1 = 0, answerNum2 = 0, answerNum3 = 0, answerNum4 = 0;
        answerNums = new int[] {answerNum1, answerNum2, answerNum3, answerNum4};

        // Using member variable to determine length
        userNumbers = new int[editTexts.length];

        // Using currentTimeMillis to calculate random number as recommended
        randomNumber = new Random(System.currentTimeMillis());
        restart();
    }

    // Steps of entering guess, comparing, updating and give results
    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // Enters the user's choice number in the text
            for(int i = 0; i < editTexts.length; i++) {
                if (checkInput(editTexts[i])) {
                    String userInput = editTexts[i].getText().toString();
                    userNumbers[i] = Integer.parseInt(userInput);
                } else {
                    // If a field is empty, a toast will inform the user
                    noTextToast();
                }
            }

            // Checks equality of strings for which ones are correct
            for (int i =0; i < editTexts.length; ++i) {
                if (userNumbers[i] == answerNums[i]) {
                    return;
                }
            }

            boolean isGameComplete = false;
            for(int i = 0; i < userNumbers.length; i++) {
                updateColor(userNumbers[i], answerNums[i], editTexts[i]);
                // Compares numbers entered by user and random number
                if(userNumbers[i] == answerNums[i]) {
                    isGameComplete = true;
                }
            }

            // Updates the amount of guesses made or game win
            if(isGameComplete) {
                // Show success dialog
                showCorrectAlerts();
            } else {
                totalGuesses--;
                // check if guess count is zero
                if (totalGuesses == 0) {
                    // If zero show failure
                    showFailAlerts();
                } else {
                    // Decrement guess count and show user by toast
                    showRemainingGuesses(totalGuesses--);
                }
            }
        }
    };

//    private void gameCheck() {

//    }

    // Eliminates white space
    private boolean checkInput(EditText number) {
        return number.getText().toString().trim().length() > 0;
    }

    // Alert dialogs for each positive action
    private void showCorrectAlerts() {
        // Sets AlertDialog class for messaging the user
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.success);
        builder.setMessage(R.string.allCorrect);
        builder.setPositiveButton(R.string.playAgain, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.restarted, Toast.LENGTH_SHORT).show();
                restart();
            }
        });
        builder.show();
    }

    // Alert dialogs for each negative action
    private void showFailAlerts() {
        // Sets AlertDialog class for messaging the user
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.failed);
        builder.setMessage(R.string.noGuesses);
        builder.setPositiveButton(R.string.playAgain, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.restarted, Toast.LENGTH_SHORT).show();
                restart();
            }
        });
        builder.show();
    }

    // Toast alert if editViews are empty
    private void noTextToast() {
        for (int i = 0; i < editTexts.length - 1; i++) {
            if (editTexts[i].getText().toString().trim().isEmpty()) {
                Toast.makeText(this, R.string.ensureSubmit,
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    // Toast alerts user to remaining guesses
    private void showRemainingGuesses(int guesses) {
        if (guesses > 1) {
            Toast.makeText(this, guesses + getString(R.string.guessesLeft), Toast.LENGTH_LONG).show();
        }
        else if (guesses == 1) {
            Toast.makeText(this, guesses + getString(R.string.oneGuessLeft), Toast.LENGTH_LONG).show();
        }
    }

    // Method changes view depending on guessed number compared to random number
    private void updateColor(int guess, int number, EditText view) {
        if (guess == number) {
            view.setTextColor(Color.GREEN);
        } else if (guess < number) {
            view.setTextColor(Color.BLUE);
        } else if (guess > number) {
            view.setTextColor(Color.RED);
        }
    }

    // Method restarts game and initializes random numbers
    private void restart() {
        // Resets text color and edit texts to empty
        for (EditText editText : editTexts) {
            editText.setTextColor(Color.BLACK);
            editText.setText("");
        }

        // Resets the number of elements in the guess range
        for (int i = 0; i < answerNums.length; i++) {
            int numOfElements = 10;
            answerNums[i] = randomNumber.nextInt(numOfElements);
        }

        // Resets # of guesses with new game
        totalGuesses = 4;
    }
}
