// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.googlebookslisting;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity.TAG";

    private static final String PHOTOS_BASE_URL =
            "http://services.hanselandpetal.com/photos/";

    private GridView mGridView;
    private ProgressBar mProgressBar;

    private List<BookGridData> bookGridDatas;
    private List<BackThreadTask> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: On");

        mGridView = (GridView) findViewById(R.id.)

        // Start progress bar, make visible
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        //Initialize with empty data
        tasks = new ArrayList<>();

        Log.d(TAG, "onCreate: Off");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.bookGridView) {
            if (isOnline()) {
                String url = "http://services.hanselandpetal.com/feeds/flowers.json";
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

    private void requestData(String uri) {
        BackThreadTask task = new BackThreadTask();

        // Runs asynch so it operates quicker by running parallel not serially
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }

    protected void updateDisplay() {
        GridViewAdapter adapter = new GridViewAdapter(this,R.id.bookGridView, bookGridDatas);
        data.setAdapter(adapter);
    }

    // Adding permissions for connectivity
    protected boolean isOnline() {
        // Check for connectivity
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        // Do network operation
//            BackThreadTask backThreadTask = new BackThreadTask();
//            backThreadTask.execute(url);
//            Toast.makeText(this, "You are connected.", Toast.LENGTH_SHORT).show();
        return info != null && info.isConnectedOrConnecting();
    }

    /*********************************** Asynch Task **********************************/

    // Class for async - must be subclass of superclass
    private class BackThreadTask extends AsyncTask<String, String, List<BookGridData>> {

        @Override
        protected void onPreExecute() { // Happens on the Main Tread but before the doInBackground
            super.onPreExecute();
            updateDisplay();

            // Checks if all tasks are complete before removing the progress bar
            if (tasks.size() == 0) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            tasks.add(this);

            Log.d(TAG, "Loop starting");
        }

        @Override
        protected List<BookGridData> doInBackground(String... params) {
            // Move network operations off the main thread
            String content = HttpManager.getNetworkData(params[0]);
            bookGridDatas = BookJSONParser.parseFeed(content);

            if (bookGridDatas != null) {
                for (BookGridData bookGridData : bookGridDatas) {
                    try {
                        String imageUrl = PHOTOS_BASE_URL + bookGridData.getPhoto();
                        InputStream inputStream = (InputStream) new URL(imageUrl).getContent();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        bookGridData.setBitmap(bitmap);
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            return bookGridDatas;
        }

        @Override
        protected void onPostExecute(List<BookGridData> result) { // Operates on the UI thread

            // Checks if all tasks are complete before removing the progress bar
            tasks.remove(this);
            if (tasks.size() == 0) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(MainActivity.this, "Unable to connect to web", Toast.LENGTH_LONG).show();
                return;
            }
            bookGridDatas = result;

            Log.d(TAG, "Loop complete");
        }

//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//
//            Log.d(TAG, "");
//        }
    }
}
