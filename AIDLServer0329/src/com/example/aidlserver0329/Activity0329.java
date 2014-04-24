package com.example.aidlserver0329;

import java.util.ArrayList;
import java.util.List;

import com.example.aidlserver0329.helper.MyHelper;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.os.RemoteException;


public class Activity0329 extends Service {
	MyAdil myAidl;
	MyHelper helper=null;
	SQLiteDatabase db=null;
	public void onCreate() {
		myAidl=new MyAdil();
		helper=new MyHelper(this);
		db=helper.getWritableDatabase();
		System.out.println(".............");
		super.onCreate();
	}
	class MyAdil extends AIDL.Stub{

		public String queryData() throws RemoteException {
			ContentValues values=new ContentValues();
			values.put("info", "aidl1");
			db.insert("aidlinfo", null, values);
			Cursor cursor=db.query("aidlinfo", new String[]{"info"}, null, null, null, null, null);
			String str=null;
			while(cursor.moveToNext()){
				 str=cursor.getString(0);
			}
			System.out.println(str+"......");
			return  str;
		}

	
		
	}
	
	public IBinder onBind(Intent intent) {
		
		return myAidl;
	}

}
