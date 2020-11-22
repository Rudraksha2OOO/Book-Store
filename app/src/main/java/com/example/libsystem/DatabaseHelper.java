package com.example.libsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "lib.db";
    public static final String TABLE_NAME = "books";
    public static final String COL1 = "BOOKID";
    public static final String COL2 = "BOOKNAME";
    public static final String COL3 = "BOOKAUTHOR";
    public static final String COL4 = "BOOKCATEGORY";
    public static final String COL5 = "BOOKPRICE";

    public static final String TABLE1_NAME = "users";
    public static final String COLS1 = "USERID";
    public static final String COLS2 = "FNAME";
    public static final String COLS3 = "LNAME";
    public static final String COLS4 = "CONTACTNO";
    public static final String COLS5 = "EMAILID";
    public static final String COLS6 = "USERNAME";
    public static final String COLS7 = "PASSWORD";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    /*public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);*/


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2+" TEXT,"+COL3+" TEXT," +
                COL4+" TEXT,"+COL5+" TEXT)");
        db.execSQL("CREATE TABLE "+TABLE1_NAME+"("+COLS1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLS2+" TEXT,"+COLS3+" TEXT," +
                COLS4+" TEXT,"+COLS5+" TEXT,"+COLS6+" TEXT,"+COLS7+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        onCreate(db);
      // if (newVersion>oldVersion){
        //   db.execSQL("ALTER TABLE BOOKS DROP COLUMN PRICE ");}
    }
    public boolean insertDatabooks(String name,String author,String category,String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,name);
        cv.put(COL3,author);
        cv.put(COL4,category);
        cv.put(COL5,price);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result==-1)
            return false;
        else
            return true;
    }
    public boolean insertDatausers(String fname,String lname,String cno,String email,String uname,String passwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLS2,fname);
        cv.put(COLS3,lname);
        cv.put(COLS4,cno);
        cv.put(COLS5,email);
        cv.put(COLS6,uname);
        cv.put(COLS7,passwd);
        long result = db.insert(TABLE1_NAME,null,cv);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean updateDatabooks(String id,String name,String author,String category,String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,name);
        cv.put(COL3,author);
        cv.put(COL4,category);
        cv.put(COL5,price);
        long result = db.update(TABLE_NAME,cv," bookid = "+id,null);
        if (result==-1)
            return false;
        else
            return true;
    }
    public boolean updateDatausers(String uid,String fname,String lname,String cno,String email,String uname,String passwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLS2,fname);
        cv.put(COLS3,lname);
        cv.put(COLS4,cno);
        cv.put(COLS5,email);
        cv.put(COLS6,uname);
        cv.put(COLS7,passwd);
        long result = db.update(TABLE1_NAME,cv," userid = "+uid,null);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllDatabooks()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cs;
    }
    public Cursor getAllDatausers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM "+TABLE1_NAME,null);
        return cs;
    }

    public Cursor searchbooks(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("Select * From "+TABLE_NAME+" where bookid = "+id,null);
        return cs;
    }
    public Cursor searchusers(String uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("Select * From "+TABLE1_NAME+" where userid = "+uid,null);
        return cs;
    }
    public Cursor searchuser(String usname,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("Select * From "+TABLE1_NAME+" where username = '"+usname+"' and password = '"+pass+"' " ,null);
        return cs;
    }

    public int deleteDatabooks(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int cn = db.delete(TABLE_NAME," bookid = "+id,null);
        return cn;
    }
    public int deleteDatausers(String uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int cn = db.delete(TABLE1_NAME," userid = "+uid,null);
        return cn;
    }
    public Cursor getListContents()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return data;
    }
    public Cursor getBookDetails(String bkname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME+" where bookname = '"+bkname+"' ",null);
        return data;
    }
    public Cursor getUserDetails(String usrname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE1_NAME+" where username = '"+usrname+"' ",null);
        return data;
    }

}
