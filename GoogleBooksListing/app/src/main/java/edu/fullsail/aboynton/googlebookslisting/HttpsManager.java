// Allen Boynton

// JAV1 - 1702

// HttpsManager.java

package edu.fullsail.aboynton.googlebookslisting;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class HttpsManager {

    // Pulls down data from the Google Books API
    static String getNetworkData(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();

            connection.disconnect();

            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
