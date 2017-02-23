// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LOG_LIFE_CYCLE";
    private static final String KEY_TEXT_LIST = "KEY_TEXT_LIST";

    private EditText userInput;

    private ArrayList<String> textList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private ListView stringListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, " ----> MainActivity.onCreate()");

        // Save the contents of the textList
        if (savedInstanceState != null) {
            textList = savedInstanceState.getStringArrayList(KEY_TEXT_LIST);
        }

        // Assigns the userInput to the editText field
        userInput = (EditText) findViewById(R.id.userInput);
        userInput.setText("");

        stringListView = (ListView) findViewById(R.id.string_listView);

        if (textList != null) {
            arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1, textList);
            // Sets the adapter
            stringListView.setAdapter(arrayAdapter);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Converts editView string to a String
                String text = userInput.getText().toString();

                // Adds userInput to the textList
                textList.add(text.trim());

                // Show a Toast Dialog
                if (text.length() == 0) {
                    textList.remove(textList.size() - 1);
                    Toast.makeText(MainActivity.this,
                            R.string.noEntryToast, Toast.LENGTH_SHORT).show();
                } else if (text.trim().length() == 0) {
                    Toast.makeText(MainActivity.this,
                            R.string.trimStringToast, Toast.LENGTH_SHORT).show();
                }
                // Sets edit text to empty after text is submitted
                userInput.setText("");

                // Sets the adapter
                stringListView.setAdapter(arrayAdapter);
            }
        };
        // Set up onClickListener for button
        findViewById(R.id.submit_button).setOnClickListener(listener);
        Log.i(LOG_TAG, " <---- MainActivity.onCreate()");
    }

    protected void onStart() {
        Log.i(LOG_TAG, " ----> MainActivity.onStart()");
        super.onStart();
    }

    // Restores the instance state preferred method but not necessary
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(LOG_TAG, " ----> MainActivity.onRestoreInstanceState(): " + "ListView restored = " + stringListView.getCount());
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onRestart() {
        Log.i(LOG_TAG, " ----> MainActivity.onRestart()");
        super.onRestart();
    }

    // Saves the instance state
    public void onSaveInstanceState(Bundle outState) {
        Log.i(LOG_TAG, " ----> MainActivity.onSaveInstanceState(): " + "ListView saved = " + stringListView.getCount());
        outState.putStringArrayList(KEY_TEXT_LIST, textList);
        super.onSaveInstanceState(outState);
    }

    protected void onPause() {
        Log.i(LOG_TAG, " ----> MainActivity.onPause()");
        super.onPause();
    }

    protected void onResume() {
        Log.i(LOG_TAG, " ----> MainActivity.onResume()");
        super.onResume();
    }

    protected void onStop() {
        Log.i(LOG_TAG, " ----> MainActivity.onStop()");
        super.onStop();
    }

    protected void onDestroy() {
        Log.i(LOG_TAG, " ----> MainActivity.onDestroy()");
        super.onDestroy();
    }
}
