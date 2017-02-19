// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce05;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**************** Declare Variables *****************/

    // Created userWords to hold all the userInput of strings
    private final ArrayList<String> userWords = new ArrayList<>();

    // Reports/Prints to the console
    private static final String TAG = "MyActivity";

    // Initialize the user's input
    private EditText userInput;

    // Number picker manages collection by use of array index
    private NumberPicker wordPicker = null;

    // Initialize textViews
    private TextView avgView;
    private TextView medianView;

    // Global variables
    private String word;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the widgets reference from XML layout
        userInput = (EditText) findViewById(R.id.userInput);
        wordPicker = (NumberPicker) findViewById(R.id.number_picker);
        avgView = (TextView) findViewById(R.id.average_output);
        medianView = (TextView) findViewById(R.id.median_output);

        // Adds strings to the userWords and calculates math
        word = userInput.getText().toString();

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: On");

                // Calls on method to setup picker
                addNumPicker();

                // Variables within onClick
                int id = view.getId();

                // If/else to determine which button is tapped and the action that follows
                if (id == R.id.add_button) {
                    // Shows toasts depending on user entry
                    addToast();
                    updateAverage();
                    updateMedian();
                }
                else if (id == R.id.view_button) {
                    if (userWords.size() > 0) {
                        userWords.trimToSize();
                        // Show an Alert Dialog
                        removalAlert();
                        updateAverage();
                        updateMedian();
                    }
                }
                Log.e(TAG, "onClick: Off");
            }
        };
        // Set up onClickListener for 2 buttons
        findViewById(R.id.add_button).setOnClickListener(listener);
        findViewById(R.id.view_button).setOnClickListener(listener);
    }

    private void addNumPicker() {
        Log.e(TAG, "addNumPicker(): On");
        //Specify the maximum value/number of NumberPicker
        wordPicker.setMaxValue(userWords.size());
        //Set the minimum value of NumberPicker
        wordPicker.setMinValue(0);
        //Gets whether the selector wheel wraps when reaching the min/max value
        wordPicker.setWrapSelectorWheel(true);
        Log.e(TAG, "addNumPicker(): Off");
    }

    private void addToast() {
        Log.e(TAG, "addToast(): On");
        String result = userInput.getText().toString();
        // Show a Toast Dialog for no entry
        if (result.isEmpty()) {
            Log.d(TAG, "addToast(): Empty field");
            Toast.makeText(MainActivity.this,
                    R.string.noEntryToast, Toast.LENGTH_SHORT).show();
        }
        else if (result.contains(" ")) { // toast if white space
            Log.d(TAG, "addToast(): Has white space");
            Toast.makeText(MainActivity.this,
                    R.string.zeroLengthToast, Toast.LENGTH_SHORT).show();
        }
        else if (userWords.contains(result)) { // toast if duplicate
            Log.d(TAG, "addToast(): Entered duplicate");
            Toast.makeText(MainActivity.this,
                    R.string.uniqueStringToast, Toast.LENGTH_SHORT).show();
        }
        else {
            userWords.add(result);

            // Log to console to get answers
            Log.d(TAG, "addToast(): Added " + result);
            // Print to console to test
            if (userWords.size() > 0) {
                for (String s : userWords) {
                    Log.d(TAG, "THE STRINGS IN THE ARRAY ARE: " + s);
                }
                Log.d(TAG, "# of elements in array: " + userWords.size());
            }
        }
        userInput.setText("");

        Log.e(TAG, "addToast(): Off");
    }

    // Alert dialogs for each negative action
    private void removalAlert() {
        Log.e(TAG, "removalAlert(): On");
        // Sets AlertDialog class for messaging the user
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Title
        builder.setTitle(R.string.wordMsgAlert);
        // Gets the word whose index was picked
        position = wordPicker.getValue();
        word = userWords.get(position);
        // Setting Dialog Message
        builder.setMessage(word);
        Log.d("Alert word: ", word); // Check in console

        // Setup of remove button to delete element
        builder.setNegativeButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // onClick within alert
                // Remove string here with .remove()
                word = userWords.get(position);
                userWords.remove(word);
                userWords.size();
                updateAverage();
                updateMedian();

                // Log the value of the element
                Log.d("onClick Removed: ", word);
                Log.d("onClick New Size: ", String.valueOf(userWords.size()));

                // Confirm with user their choice with a toast
                Toast.makeText(MainActivity.this, R.string.alert_removed_confirmation, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        // Setup of cancel button to dismiss dialog
        builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
                // Confirm with user their choice with a toast
                Toast.makeText(MainActivity.this, R.string.alert_toast_closed, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
        Log.e(TAG, "removalAlert(): Off");
    }

    private void updateAverage() {
        // Variables to get average
        double aNumber;
        double sum = 0d;
        double avg = 0d;

        for(int i = 0; i < userWords.size(); i++) {
            aNumber = userWords.get(i).length();
            sum = sum + aNumber;
            avg = sum / userWords.size();
        }
        avgView.setText(String.valueOf(avg));
    }

    private void updateMedian() {
//        Collections.sort(userWords);
//        double median = 0d;
//        double mNumber = 0d;
//
//        Object[] newArray = userWords.toArray();
//        for (int i = 0; i < userWords.size(); i++) {
//            if (userWords.size() % 2 == 0) {
//                mNumber = userWords.get(i).length();
//                median = newArray[(int) (mNumber / 2)] + (double) newArray[(int) (mNumber / 2) - 1] / 2);
//            } else {
//                median = (double) newArray[(int) (mNumber / 2)];
//            }
//            medianView.setText(String.valueOf(median));
//        }
    }
}
