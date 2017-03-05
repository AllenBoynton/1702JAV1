// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce09;

import android.content.Context;
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

import edu.fullsail.aboynton.boyntonallen_ce09.network.GetBooksTask;
import edu.fullsail.aboynton.boyntonallen_ce09.network.NetworkConnection;
import edu.fullsail.aboynton.boyntonallen_ce09.object.Book;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.TAG";
    private final Context mContext = null;
    private GridView bookGridView;
    private ProgressBar mProgressBar;
    private ArrayList<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: On");

        bookGridView = (GridView) findViewById(R.id.bookGridView);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        // Start progress bar, make visible
        mProgressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "onCreate: Off");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, " ----> onOptionsItemSelected()");
        // GridView
        if (item.getItemId() == R.id.bookGridView) {
            if (NetworkConnection.isOnline(mContext)) {
                requestData();
            } else {
                Toast.makeText(this, R.string.noConnectionToast, Toast.LENGTH_LONG).show();
            }
        }
        Log.i(TAG, " <---- onOptionsItemSelected()");
        return false;
    }

    private void requestData() {
        Log.i(TAG, " ----> requestData()");

        String url = "https://www.googleapis.com/books/v1/volumes?q=scorpions&orderBy=newest&key=AIzaSyDA1ZH96CVwdIshPOzfCUsWjKpibS6_JO8";

        GetBooksTask getBooksTask = new GetBooksTask();
        // Runs asynch so it operates quicker by running parallel not serially
        getBooksTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        Log.i(TAG, " <---- requestData()");
    }

    // Use BooksAdapter to display data
    public void updateDisplay() {
        Log.i(TAG, " ----> updateDisplay()");

        bookGridView.setAdapter(new BooksAdapter(this, bookList));

        Log.i(TAG, " <---- updateDisplay()");
    }
}