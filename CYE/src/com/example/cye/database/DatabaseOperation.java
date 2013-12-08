package com.example.cye.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.cye.app.MyApp;
import com.example.cye.util.BufferedRaf;
import com.example.cye.util.ItemInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class DatabaseOperation {

	SQLiteDatabase db;
	Database dbHelper;

	public DatabaseOperation(Context context) {
		Database database = new Database(context);
		dbHelper = database;
	}

	public void addDatabase() {
		Charset chartset = Charset.forName("utf-8");
		open();

		SQLiteStatement statement = db.compileStatement("insert into cye values(?, ?, ?);");
		
		try {
			BufferedRaf bf = new BufferedRaf(new File(MyApp.PATH + File.separator + MyApp.FILE_TEXT), "r");
			String lineKeyword;
			String lineTitle;
			String lineContent;
			db.beginTransaction();
			while ((lineKeyword = bf.readLine2()) != null) {
				lineTitle = bf.readLine2();
				StringBuilder contenBuilder = new StringBuilder();
				while ((lineContent = bf.readLine2()) != null && (lineContent.trim().length()) != 0) {
					contenBuilder.append(lineContent);
				}

				statement.clearBindings();
				statement.bindString(1, lineKeyword);
				statement.bindString(2, new String(lineTitle.getBytes("iso-8859-1"), chartset));
				statement.bindString(3, new String(contenBuilder.toString().getBytes("iso-8859-1"),
				        chartset));
				
				statement.execute();
			}
			
			db.setTransactionSuccessful();
			db.endTransaction();
			bf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}

	public void close() {
		if (db.isOpen()) {
			db.close();
		}
	}

	public void getAllTitle(List<ItemInfo> list) {
		open();
		String sql = "select rowid, " + Database.COL_TITLE + " from " + Database.TABLE;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					addOneItem(list, cursor);
				} while (cursor.moveToNext());
			}
			close();
		}
	}

	public String getContent(int rowid) {

		open();
		String sql = "select " + Database.COL_CONTENT + " from " + Database.TABLE
		        + " where rowid = " + rowid;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			return cursor.getString(0);
		}
		return null;

	}

	public void open() {

		db = SQLiteDatabase.openDatabase(MyApp.PATH + File.separator + Database.DATABASE, null,
		        SQLiteDatabase.OPEN_READWRITE);
	}

	public void searchKeyword(String keyword, List<ItemInfo> list) {
		open();
		String sql = "select rowid, " + Database.COL_TITLE + " from " + Database.TABLE +  " where " + Database.COL_KEYWORD
		        + " like ? ";

		Cursor cursor = db.rawQuery(sql, new String[]{"%" + keyword + "%"});
		if (cursor.moveToFirst()) {
			do {
				addOneItem(list, cursor);
			} while (cursor.moveToNext());
		}
		close();
	}

	private void addOneItem(List<ItemInfo> list, Cursor cursor) {
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setId(cursor.getInt(0));
		itemInfo.setTitle(cursor.getString(1));
		list.add(itemInfo);
	}
}
