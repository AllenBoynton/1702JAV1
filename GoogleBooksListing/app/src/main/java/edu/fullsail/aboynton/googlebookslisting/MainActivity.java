// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.googlebookslisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity.TAG";

    private TextView textView;
    private GridView mGridView;
    private ProgressBar mProgressBar;
    private GridViewAdapter mGridAdapter;
    private ArrayList<BookGridData> mGridData;
    private List<BookGridData> bookGridDatas;
    private List<BackThreadTask> tasks;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=scorpions&orderBy=newest&key=AIzaSyDA1ZH96CVwdIshPOzfCUsWjKpibS6_JO8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: On");

        textView = (TextView) findViewById(R.id.textView);
        mGridView = (GridView) findViewById(R.id.bookGridView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Initialize with empty data
        bookGridDatas = new ArrayList<>();
        mGridData = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, mGridData);
//        mGridView.setAdapter(mGridAdapter);

        // Start download
        new BackThreadTask().execute(url);

        // Start progress bar, make visible
        mProgressBar.setVisibility(View.VISIBLE);
        tasks = new ArrayList<>();

        Log.d(TAG, "onCreate: Off");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.bookGridView) {
            if (isOnline()) {
                requestData(url);
            }
            else {
                Toast.makeText(this, "No connectivity.", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }

    private void requestData(String uri) {
        BackThreadTask task = new BackThreadTask();

        // Runs asynch so it operates quicker by running parallel not serially
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }

    protected void updateDisplay(String message) {
        mGridView.setAdapter(mGridAdapter);
        textView.setText(message);
        System.out.println(message);
    }

    // Adding permissions for connectivity
    protected boolean isOnline() {
        // Check for connectivity
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnectedOrConnecting()) {
            // Do network operation
            BackThreadTask backThreadTask = new BackThreadTask();
            backThreadTask.execute(url);
            Toast.makeText(this, "You are connected.", Toast.LENGTH_SHORT).show();

            return true;
        }
        else {
            return false;
        }
    }

    /*********************************** Asynch Task **********************************/

    // Class for async - must be subclass of superclass
    private class BackThreadTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() { // Happens on the Main Tread but before the doInBackground
            updateDisplay("Starting task");  //super.onPreExecute();

            // Checks if all tasks are complete before removing the progress bar
            if (tasks.size() == 0) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            tasks.add(this);

            Log.d(TAG, "Loop starting");
        }

        @Override
        protected String doInBackground(String... params) {
            // Move network operations off the main thread
            return HttpsManager.getNetworkData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) { // Operates on the UI thread

            // Checks if all tasks are complete before removing the progress bar
            tasks.remove(this);
            if (tasks.size() == 0) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(MainActivity.this, "Unable to connect to web", Toast.LENGTH_LONG).show();
                return;
            }

            bookGridDatas = BookJSONParser.parseFeed(result);
            updateDisplay(result);

            Log.d(TAG, "Loop complete");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            Log.d(TAG, "");
        }
    }
}
