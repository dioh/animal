package com.animal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text = (TextView) findViewById(R.id.texto);
		
		String path1 = Environment.getExternalStorageDirectory().toString() + "/Pictures";
		String path2 = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera";
		File file1 = new File(path1);
		File file2 = new File(path2);
		File array1[] = file1.listFiles();
		File array2[] = file2.listFiles();
		List<File> archivos = new ArrayList<File>();
		if (array1 != null)
			for (int i = 0; i < array1.length; i++) archivos.add(array1[i]);
		if (array2 != null)
			for (int i = 0; i < array2.length; i++) archivos.add(array2[i]);
		
		StringBuilder s = new StringBuilder();
		for (File file : archivos) {
			if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") ) {
				s.append(file.getName() + "\n");
			}
		}
		
		text.setText(s);
		
		Toast.makeText(getBaseContext(), "Terminado", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
