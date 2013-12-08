package com.example.cye.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public static final String COL_CONTENT = "content";
	public static final String COL_KEYWORD = "keyword";
	public static final String COL_TITLE = "title";
	public static final String DATABASE = "cye.db";
	public static final String TABLE = "cye";
	public static final int VERSION = 1;

	public static final String CREATING_TABLE_SQL = "create table " + TABLE + " (" + COL_KEYWORD
	        + ", " + COL_TITLE + ", " + COL_CONTENT + ")";

	public Database(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table cye(keyword, title, content)");
	}

	public void onUpgrade(SQLiteDatabase sqlitedatabase, int oldVersion, int newVersion) {
	}
}
