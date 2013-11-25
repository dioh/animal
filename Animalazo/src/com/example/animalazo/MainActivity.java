package com.example.animalazo;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
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
			contactsCollapsed += " " + contact;
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

		ArrayList<String> contacts = new ArrayList<String>();

		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null); 
		while (cursor.moveToNext()) { 
			String contact = "";
			String contactId = cursor.getString(cursor.getColumnIndex( 
					ContactsContract.Contacts._ID));

			//id
			contact = contactId;

			// Name
//			String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); 
//			if (Boolean.parseBoolean(hasPhone)) { 
//				// You know it has a number so now query it like this
//				Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Identity.CONTENT_URI, 
//								null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, 
//								null, null); 
//				while (phones.moveToNext()) { 
//					String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));                 
//					// Phones
//					contact += ";" + phoneNumber ;
//				} 
//				phones.close(); 
//			}
			
			// Phone
			String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
			if (Boolean.parseBoolean(hasPhone)) { 
				// You know it has a number so now query it like this
				Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null); 
				while (phones.moveToNext()) { 
					String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));                 
					// Phones
					contact += ";" + phoneNumber ;
				} 
				phones.close(); 
			}
			
			Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null); 
			while (emails.moveToNext()) { 
				// This would allow you get several email addresses 
				String emailAddress = emails.getString( 
						emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
				contact += ";" + emailAddress;
			} 
			emails.close();
			contacts.add(contact);
		}

		cursor.close(); 

		return contacts;

	}

}
