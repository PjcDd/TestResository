package com.example.ch17_1;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Activity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Uri contacts = Uri.parse("content://contacts/people");
		Cursor c = getContentResolver().query(contacts, null, null, null, null);
		startManagingCursor(c);
		String[] columns = new String[]{
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts._ID
		};
		int[] views = new int[]{
				R.id.textView1,
				R.id.textView2,
		};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.activity_main,c,columns,views);
		setListAdapter(adapter);
	}

	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		TextView lblID = (TextView) v.findViewById(R.id.textView1);
		TextView lblName = (TextView) v.findViewById(R.id.textView2);
		String cid = lblID.getText().toString();
		String name = lblName.getText().toString();
		Cursor pCur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
				null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
				new String[] {cid}, null);
		while(pCur.moveToNext()){
			String phoneNo=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			Toast.makeText(this, "ÐÕÃû£º"+name+"\n µç»°ºÅÂë£º "+phoneNo,Toast.LENGTH_SHORT).show();
		}
		pCur.close();
		
	}

	
}
