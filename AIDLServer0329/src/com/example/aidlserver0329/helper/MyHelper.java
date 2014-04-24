package com.example.aidlserver0329.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
	private static final String DB_PATH="aidl.db";
	private static final int DB_VERSION=1;
	public MyHelper(Context context){
		
		super(context, DB_PATH, null, DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		String sql="create table aidlinfo(_id Integer primary key autoincrement," +
				"info text)";
		db.execSQL(sql);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
