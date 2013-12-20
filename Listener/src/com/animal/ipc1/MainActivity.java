package com.animal.ipc1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	protected void onResume()
	{
		super.onResume();
        setContentView(R.layout.activity_main);
        
        TextView text = (TextView) findViewById(R.id.textView1);
        
    	FileInputStream fis;
    	try {
			fis = openFileInput("pressed");
			
	    	StringBuffer fileContent = new StringBuffer("");
	
	    	byte[] buffer = new byte[1024];
	
	    	while (fis.read(buffer) != -1) {
	    	    fileContent.append(new String(buffer));
	    	}
	    	fis.close();
	    	
	    	text.setText(fileContent);
	    	
	    	new RequestTask(this).execute("http://"+ this.getAddress() +"/key", fileContent.toString());
	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	WebView web = (WebView) findViewById(R.id.webView1);
    	web.loadUrl("http://thecatapi.com/api/images/get?format=html&size=small");
	}
    
    public void dataSent()
    {
		try {
			FileOutputStream fos = openFileOutput("pressed", MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	private String getAddress() {
		try {
			Properties properties = new Properties();
			properties.load(this.getAssets().open("server.properties"));
			return properties.getProperty("address");
		} catch (Exception e) {
			return null;
		}
	}
    
}
