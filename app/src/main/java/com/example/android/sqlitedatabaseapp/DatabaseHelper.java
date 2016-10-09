package com.example.android.sqlitedatabaseapp;

  import android.content.ContentValues;
  import android.content.Context;
  import android.database.Cursor;
  import android.database.sqlite.SQLiteDatabase;
  import android.database.sqlite.SQLiteOpenHelper;
  import android.util.Log;

/**
 * Created by Raushan on 10/8/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

  public static final String DATABASE_STRING = "student.db";
  public static final String TABLE_NAME = "student_table";
  public static final String COL_1 = "ID";
  public static final String COL_2 = "NAME";
  public static final String COL_3 = "SURNAME";
  public static final String COL_4 = "MARKS";


  public DatabaseHelper(Context context) {
    super(context, DATABASE_STRING, null, 1);

  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    try {
      db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER)");
    } catch (Exception ex) {
      Log.d("OnCreate", ex.getMessage());
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);

  }

  public boolean insertData(String name, String surname, String marks) {

    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_2, name);
    contentValues.put(COL_3, surname);
    contentValues.put(COL_4, marks);
    long reult = db.insert(TABLE_NAME, null, contentValues);

    if (reult == -1)
      return false;
    else
      return true;
  }

  public Cursor getAllData (){
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor res = db.rawQuery("select *" + TABLE_NAME,null);
    return res;

  }


}

