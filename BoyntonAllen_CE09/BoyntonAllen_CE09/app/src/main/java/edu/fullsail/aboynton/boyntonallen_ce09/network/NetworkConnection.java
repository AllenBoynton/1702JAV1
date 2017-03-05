// Allen Boynton

// JAV1 - 1702

// NetworkConnection.java

package edu.fullsail.aboynton.boyntonallen_ce09.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkConnection {

    // Adding permissions for connectivity
    public static boolean isOnline(Context context) {
        // Check for connectivity
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info != null && info.isConnectedOrConnecting();
    }

    // Pulls down data from the Google Books API
    static String getNetworkData(String urlString) {
        String data = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            data = IOUtils.toString(inputStream);
            inputStream.close();

            connection.disconnect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
