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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.TAG";

    private GridView gridView;
    private ProgressBar progressBar;

    private ArrayList<BookGridData> mBookItems;
    private List<BookGridData> dataList;

    private final String url = "https://www.googleapis.com/books/v1/volumes?q=scorpions&orderBy=newest&key=AIzaSyDA1ZH96CVwdIshPOzfCUsWjKpibS6_JO8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: On");

        gridView = (GridView) findViewById(R.id.bookGridView);

        // Start progress bar, make visible
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //Start download
        new BackThreadTask().execute(url);

        mBookItems = new ArrayList<>();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void requestData(String url) {
        BackThreadTask task = new BackThreadTask();

        // Runs asynch so it operates quicker by running parallel not serially
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    private void updateDisplay() {
        GridViewAdapter adapter = new GridViewAdapter(this, mBookItems);
        gridView.setAdapter(adapter);
    }

    // Adding permissions for connectivity
    private boolean isOnline() {
        // Check for connectivity
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        // Do network operation
        boolean isConnected = info.isConnected();

        if (isConnected) {
            // Do network operation
            BackThreadTask backThreadTask = new BackThreadTask();
            backThreadTask.execute(url);
            Toast.makeText(this, "You are connected.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No connectivity", Toast.LENGTH_SHORT).show();
        }
        return info.isConnectedOrConnecting();
    }

    /*********************************** Async Task **********************************/

    // Class for async - must be subclass of superclass
    private class BackThreadTask extends AsyncTask<String, String, List<BookGridData>> {

        @Override
        protected void onPreExecute() { // Happens on the Main Tread but before the doInBackground
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

            Log.d(TAG, "Loop starting");
        }

        @Override
        protected List<BookGridData> doInBackground(String... params) {
            // Move network operations off the main thread
            String content = HttpsManager.getNetworkData(params[0]);
            dataList = BookJSONParser.parseFeed(content);

            return dataList;
        }

        @Override
        protected void onPostExecute(List<BookGridData> result) { // Operates on the UI thread

            progressBar.setVisibility(View.INVISIBLE);

            if (result == null) {
                Toast.makeText(MainActivity.this, "Unable to connect to web", Toast.LENGTH_LONG).show();
                return;
            }
            dataList = result;
            updateDisplay();
            Log.d(TAG, "Loop complete");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            Log.d(TAG, "");
        }
    }
}
