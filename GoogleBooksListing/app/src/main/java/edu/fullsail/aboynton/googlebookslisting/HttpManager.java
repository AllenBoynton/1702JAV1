// Allen Boynton

// JAV1 - 1702

// HttpManager.java

package edu.fullsail.aboynton.googlebookslisting;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {

    // Pulls down data from the Google Books API
    static String getNetworkData(String urlString) {

        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.connect();

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

//            InputStream is = connection.getInputStream();
//            String data = IOUtils.toString(is);
//            is.close();
//
//            connection.disconnect();
//
//            return data;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
