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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    /********************** Declare Variables *********************/

    // Created arrayList to hold all the userInput of strings
    private final ArrayList<String> arrayList = new ArrayList<>();

    // Alert dialog builder instantiated
    private AlertDialog.Builder builder;

    // Reports/Prints to the console
    private static final String TAG = "MyActivity";

    // Initialize the user's input
    private TextView userInput;
    private String word;

    // Number picker manages collection by use of array index
    private NumberPicker wordPicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the widgets reference from XML layout
        userInput = (TextView) findViewById(R.id.userInput);
        wordPicker = (NumberPicker) findViewById(R.id.number_picker);

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: On");

                // Variables within onClick
                int id = view.getId();

                String text = userInput.getText().toString();
                arrayList.add(text);

                addNumPicker();

                // If/else to determine which button is tapped and the action that follows
                if (id == R.id.add_button) {
                    // Shows toasts depending on user entry
                    addToast();
                }
                else if (id == R.id.view_button) {
                    if (arrayList.size() > 0) {
                        // Show an Alert Dialog
                        removalAlert();
                    }
                    else {
                        return;
                    }
                }
                else {
                    Log.e(TAG, "Unknown criteria " + id);
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
        wordPicker.setMaxValue(arrayList.size());
        //Set the minimum value of NumberPicker
        wordPicker.setMinValue(0);
        //Gets whether the selector wheel wraps when reaching the min/max value
        wordPicker.setWrapSelectorWheel(false);

        Log.e(TAG, "addNumPicker(): Off");
    }

    private void addToast() {
        Log.e(TAG, "addToast(): On");

        // Convert textView string to a String
        word = userInput.getText().toString();

        // Show a Toast Dialog for no entry
        if (word.isEmpty()) {
            Log.d(TAG, "addToast(): Empty field");
            Toast.makeText(MainActivity.this,
                    R.string.noEntryToast, Toast.LENGTH_SHORT).show();
        }
        else if (word.contains(" ")) { // toast if white space
            Log.d(TAG, "addToast(): Has white space");
            Toast.makeText(MainActivity.this,
                    R.string.zeroLengthToast, Toast.LENGTH_SHORT).show();
//        }
//        else if (arrayList.contains(word)) { // toast if duplicate
//            Log.d(TAG, "addToast(): Entered duplicate");
//            Toast.makeText(MainActivity.this,
//                    R.string.uniqueStringToast, Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d(TAG, "addToast(): Added " + word);
            // Print to console to test
            if (arrayList.size() > 0) {
                for (String s : arrayList) {
                    Log.d(TAG, "THE STRINGS IN THE ARRAY ARE: " + s);
                }
                Log.d(TAG, "# of elements in array: " + arrayList.size());
            }
        }
        userInput.setText("");

        Log.e(TAG, "addToast(): Off");
    }

    // Alert dialogs for each negative action
    private void removalAlert() {
        Log.e(TAG, "removalAlert(): On");
        final int position;
        word = userInput.getText().toString();

        // Sets AlertDialog class for messaging the user
        builder = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Title
        builder.setTitle(R.string.wordMsgAlert);

        position = wordPicker.getValue();
        word = arrayList.get(position - 1);
        // Setting Dialog Message
        builder.setMessage(word);
        Log.d("Alert word: ", word); // Check in console

        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // onClick within alert
                // Remove string here with .remove()
                int position = wordPicker.getValue();
                word = arrayList.get(position - 1);
                arrayList.remove(word);
                arrayList.size();

                // Log the value of the element
                Log.d("onClick Removed: ", word);

                // Confirm with user their choice with a toast
                Toast.makeText(MainActivity.this, R.string.alert_removed_confirmation, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        // Setup of cancel button to dismiss dialog
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
                dialog.cancel();
                // Confirm with user their choice with a toast
                Toast.makeText(MainActivity.this, R.string.alert_toast_closed, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();

        Log.e(TAG, "removalAlert(): Off");
    }
}
