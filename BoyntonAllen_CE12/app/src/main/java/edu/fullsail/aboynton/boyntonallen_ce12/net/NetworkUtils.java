// Allen Boynton

// JAV1 - 1702

// NetworkUtils.java

package edu.fullsail.aboynton.boyntonallen_ce12.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

	public boolean isConnected(Context _context) {

		ConnectivityManager mgr = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = mgr.getActiveNetworkInfo();

		return info.isConnectedOrConnecting();
	}

	static String getNetworkData(String _url) {
		String data = null;

		try {
			URL url = new URL(_url);
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
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