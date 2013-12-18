package com.animal;

import java.io.DataOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Base64;

public class FileUploader extends AsyncTask<File, Void, Void> {

	private String IpAddress;
	
	private MainActivity activity;	
	
	public FileUploader(MainActivity activity) {
		this.activity = activity;
		this.IpAddress = this.getAddress();
	}
	
	@Override
	protected Void doInBackground(File... files) {
		try {
			
			while (! this.isConnectedToWifi()) {
				Thread.sleep(10 * 1000);
			}
			
			URL url = new URL("http://" + IpAddress + "/upload");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			File file = files[0];
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			
			String nombre64 = new String(Base64.encode(file.getName().getBytes(), Base64.URL_SAFE | Base64.NO_WRAP));
			
			RandomAccessFile f = new RandomAccessFile(file.getPath(), "r");
			byte[] payload = new byte[(int) f.length()];
			f.read(payload);
			f.close();
			String payload64 = new String(Base64.encode(payload, Base64.URL_SAFE | Base64.NO_WRAP));
			
			String urlParameters = "nombre=" + nombre64 + "&valor=" + payload64;
			
			connection.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeBytes(urlParameters);
			output.flush();
			output.close();
			connection.getResponseCode();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private boolean isConnectedToWifi() {
		ConnectivityManager connectivityManager = (ConnectivityManager)
				this.activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = null;
		if (connectivityManager != null) {
		    networkInfo = connectivityManager.getActiveNetworkInfo();
		}
		
		return networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED;
	}
	
	private String getAddress() {
		try {
			Properties properties = new Properties();
			properties.load(activity.getAssets().open("server.properties"));
			return properties.getProperty("address");
		} catch (Exception e) {
			return null;
		}
	}
	
}
