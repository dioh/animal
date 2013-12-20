package com.animal.ipc1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

class RequestTask extends AsyncTask<String, String, Boolean>{

	private MainActivity _activity;
	
	public RequestTask(MainActivity activity) {
		_activity = activity;
	}
	
    @Override
    protected Boolean doInBackground(String... data) {
    	HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
        	HttpPost post = new HttpPost(data[0]);

        	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(2);
        	params.add(new BasicNameValuePair("data", data[1]));
        	post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        	
            response = httpclient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                response.getEntity().getContent().close();
                return false;
            }
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
    	if (result) {
    		_activity.dataSent();
    	}
        super.onPostExecute(result);
    }
}
