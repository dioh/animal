package com.animal.ipc1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView text;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        text = (TextView) findViewById(R.id.textView1);
        
        findViewById(R.id.button1).setOnClickListener(mButton1_OnClickListener);
        findViewById(R.id.button2).setOnClickListener(mButton2_OnClickListener);
        
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
    
    public void pressed(String buttonName)
    {
    	try {
    		FileOutputStream fos = openFileOutput("pressed", MODE_APPEND | MODE_PRIVATE);
    		fos.write(buttonName.getBytes());
    		fos.close();
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //On click listener for button1
    final OnClickListener mButton1_OnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			pressed("A");
		}
    };
    
    //On click listener for button1
    final OnClickListener mButton2_OnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			pressed("B");
		}
    };
   
}
