package com.example.animalazo;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<String> listContacts = getContactsStrings();
		
		setContentView(R.layout.activity_main);
		TextView textView = (TextView) findViewById(R.id.textView1);
		String contactsCollapsed = "";
		for (String contact : listContacts) {
			contactsCollapsed += "|" + contact;
		}
		textView.setText(contactsCollapsed);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		
		return true;
	}

	public ArrayList<String> getContactsStrings(){
		// FIXME: Projections like this only works when contacts have both, name and phone
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
		    // Do work...
		} while (people.moveToNext());

		return contacts;

	}

}
