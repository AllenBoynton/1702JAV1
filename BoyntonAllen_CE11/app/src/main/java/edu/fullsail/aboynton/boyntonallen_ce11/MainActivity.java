// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "LIFECYCLES";
    private static final String TEXT_CONTENTS = "TextContents";

    private EditText userInput;

    private ArrayList<String> textList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private ListView stringListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: in");

        // Save the contents of the textList
        if (savedInstanceState != null) {
            textList = savedInstanceState.getStringArrayList(TEXT_CONTENTS);
        }

        // Assigns the userInput to the editText field
        userInput = (EditText) findViewById(R.id.userInput);
        userInput.setText("");

        stringListView = (ListView) findViewById(R.id.string_listView);

        if (textList != null) {
            arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1, textList);
            stringListView.setAdapter(arrayAdapter);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Converts editView string to a String
                String text = userInput.getText().toString();

                // Adds userInput to the textList
                textList.add(text);

                // Sets edit text to empty after text is submitted
                userInput.setText("");

                // Show a Toast Dialog
                if (text.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.noEntryToast, Toast.LENGTH_SHORT).show();
                }
                else if (text.trim().contains(" ")) {
                    userInput.setText(null);
                    Toast.makeText(MainActivity.this, R.string.trimStringToast, Toast.LENGTH_SHORT).show();
                }
                // Sets the adapter
                stringListView.setAdapter(arrayAdapter);
            }
        };
        // Set up onClickListener for button
        findViewById(R.id.submit_button).setOnClickListener(listener);
        Log.d(TAG, "onCreate: out");
    }

    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    // Restores the instance state preferred method but not necessary
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState IN: " + "ListView restored = " + stringListView.getCount());
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState OUT: " + "ListView restored = " + stringListView.getCount());
    }

    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

    // Saves the instance state
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceState IN: " + "ListView saved: " + stringListView.getCount());
        savedInstanceState.putStringArrayList(TEXT_CONTENTS, textList);
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState OUT: " + "ListView saved: " + stringListView.getCount());
    }

    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    protected void onResume() {
        Log.d(TAG, "onResume: in");
        super.onResume();
        Log.d(TAG, "onResume: out");
    }

    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy: in");
        super.onDestroy();
        Log.d(TAG, "onDestroy: out");
    }
}
