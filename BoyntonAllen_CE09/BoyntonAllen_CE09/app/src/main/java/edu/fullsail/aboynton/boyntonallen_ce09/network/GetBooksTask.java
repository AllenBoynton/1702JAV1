package edu.fullsail.aboynton.boyntonallen_ce09.network;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.fullsail.aboynton.boyntonallen_ce09.MainActivity;
import edu.fullsail.aboynton.boyntonallen_ce09.object.Book;


// Downloading data asynchronously
public class GetBooksTask extends AsyncTask<String, Integer, ArrayList<Book>> {

    private static final String TAG = "GetBooksTask.TAG";
    private ArrayList<Book> bookList = new ArrayList<>();
    private static final String url = "https://www.googleapis.com/bookList/v1/volumes?q=scorpions&orderBy=newest&key=AIzaSyDA1ZH96CVwdIshPOzfCUsWjKpibS6_JO8";

    // Happens on the Main Tread but before the doInBackground
    @Override
    public void onPreExecute() {
        super.onPreExecute();
//        mainActivity.mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<Book> doInBackground(String... params) {
        Log.d(TAG, " ----> doInBackground()");

        // Data retrieved from url we are working with
        String data = edu.fullsail.aboynton.boyntonallen_ce09.network.NetworkConnection.getNetworkData(url);

        try {
            JSONObject outerMostObject = new JSONObject(data);
            JSONArray booksJson = outerMostObject.getJSONArray("items");

            for (int i = 0; i < booksJson.length(); i++) {
                JSONObject items = booksJson.getJSONObject(i);
                JSONObject volumeInfo = items.getJSONObject("volumeInfo");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");

                // title is under volumeInfo category
                String title = volumeInfo.getString("title");
                // thumbnail is under imageLinks which is under volumeInfo
                String thumbnail = imageLinks.getString("thumbnail");

                bookList.add(new Book(title, thumbnail));

                Log.i(TAG, " ----> doInBackground(): Title: " + title + ", Image: " + thumbnail);
            }

            return bookList;

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, " <---- doInBackground()");
        return null;
    }

    // Operates on the UI thread
    protected void onPostExecute(ArrayList<Book> result) {
        super.onPostExecute(result);

//        mainActivity.mProgressBar.setVisibility(View.INVISIBLE);

        bookList = result;
        MainActivity m = new MainActivity();
        m.updateDisplay();

        Log.d(TAG, "Loop complete");
    }
}
