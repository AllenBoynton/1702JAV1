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
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;

    // Initialize number holder set by user
    private int userNumber1;
    private int userNumber2;
    private int userNumber3;
    private int userNumber4;

    // Initialize number to be set as random
    private Integer value1;
    private Integer value2;
    private Integer value3;
    private Integer value4;

    // Initialize user's number of guesses as 4 and counting down as button is tapped
    private int numberOfGuesses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set views for each editText view
        editText1 = (EditText) findViewById(R.id.number_one);
        editText2 = (EditText) findViewById(R.id.number_two);
        editText3 = (EditText) findViewById(R.id.number_three);
        editText4 = (EditText) findViewById(R.id.number_four);

        // Set OnClick for submit button
        findViewById(R.id.submit_guess).setOnClickListener(listener);

        // Using currentTimeMillis to calculate random number as recommended
        randomNumber = new Random(System.currentTimeMillis());
        restart();
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //TODO: Attempted to loop the addNumber method but neither For nor For each loop worked
            // Enters the user's choice number in the text
            if (addNumber(editText1) && addNumber(editText2) && addNumber(editText3) && addNumber(editText4)) {
                userNumber1 = Integer.parseInt(editText1.getText().toString());
                userNumber2 = Integer.parseInt(editText2.getText().toString());
                userNumber3 = Integer.parseInt(editText3.getText().toString());
                userNumber4 = Integer.parseInt(editText4.getText().toString());
            } else {
                // If a field is empty, a toast will inform the user
                noTextToast();
            }

            // Check if guessed number is equal to hidden number
            if (userNumber1 == value1 && userNumber2 == value2 && userNumber3 == value3 && userNumber4 == value4) {
                showCorrectAlerts();
                return;
            }

            // Figures the amount of guesses made
            numberOfGuesses++;
            if (numberOfGuesses == 4) {
                showFailAlerts();
                return;
            }
            else if (numberOfGuesses < 1) {
                showFailAlerts();
                return;
            }

            // Method calls parameters to determine color (correct/failed)
            // I attempted 3 loops for each variable but color did not update at all
            updateColor(userNumber1, value1, editText1);
            updateColor(userNumber2, value2, editText2);
            updateColor(userNumber3, value3, editText3);
            updateColor(userNumber4, value4, editText4);

            // Method call figures # of guesses left
            showRemainingGuesses(4 - numberOfGuesses);
        }
    };

    private boolean addNumber(EditText number) {
        // Eliminates white space
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
        EditText editTexts[] = {editText1, editText2, editText3, editText4};
        for (int i = 0; i < editTexts.length-1; i++) {
            if (editTexts[i].getText().toString().trim().equals("")) {
                Toast.makeText(this, R.string.ensureSubmit, Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    // Toast alerts user to remaining guesses
    private void showRemainingGuesses(int guesses) {
        if (guesses > 1) {
            Toast.makeText(this, guesses + getString(R.string.guessesLeft), Toast.LENGTH_LONG).show();
        } else if (guesses == 1) {
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
        EditText[] editTexts = {editText1, editText2, editText3, editText4};
        for (EditText editText : editTexts) {
            editText.setTextColor(Color.BLACK);
            editText.setText("");
        }
        // Attempted loop. All values were not reached whether a For or For each loop
        value1 = randomNumber.nextInt(10);
        value2 = randomNumber.nextInt(10);
        value3 = randomNumber.nextInt(10);
        value4 = randomNumber.nextInt(10);

        // Resets # of guesses with new game
        showRemainingGuesses(4);
    }
}
