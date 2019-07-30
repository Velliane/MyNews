package com.menard.mynews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseSQLite extends SQLiteOpenHelper {

    private static final String NAME_DATEBASE = "mynews.db";
    private static final int VERSION_DATABASE = 1;

    private static final String TABLE_ALREADY_READ_ARTICLES = "table_already_read_articles";
    private static final String COLUMN_URL_READ = "Url";
    private static final String CREATE_TABLE_READ = "CREATE TABLE "+ TABLE_ALREADY_READ_ARTICLES +
            " ("+ COLUMN_URL_READ + " TEXT PRIMARY KEY NOT NULL);";

    private static final String TABLE_NOTIFY_ARTICLES = "table_notify_articles";
    private static final String COLUMN_URL_NOTIFIED = "Url";
    private static final String CREATE_TABLE_NOTIFIED = "CREATE TABLE "+ TABLE_NOTIFY_ARTICLES +
            " ("+ COLUMN_URL_NOTIFIED + " TEXT PRIMARY KEY NOT NULL);";


    public BaseSQLite(@Nullable Context context) {
        super(context, NAME_DATEBASE, null, VERSION_DATABASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_READ);
        db.execSQL(CREATE_TABLE_NOTIFIED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ALREADY_READ_ARTICLES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NOTIFY_ARTICLES);
        onCreate(db);
    }

    /**
     * Open the database
     */
    private SQLiteDatabase open() {
        return this.getWritableDatabase();
    }


    //-- METHODS FOR TABLE_ALREADY_READ_ARTICLES --

    /**
     * Add new URL
     */
    public void addNewURL(String url){
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL_READ, url);

        open().insertWithOnConflict(TABLE_ALREADY_READ_ARTICLES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    /**
     * Check if URL already saved
     * @param url the URL
     * @return true or false
     */
    public boolean checkURL(String url){
        Cursor cursor = open().rawQuery("SELECT * FROM " + TABLE_ALREADY_READ_ARTICLES + " WHERE "+
                COLUMN_URL_READ + " =\"" + url + "\"", null);
        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }else {
            cursor.close();
            return false;
        }
    }


    //-- METHODS FOR TABLE_NOTIFY_ARTICLES --

    /**
     * Add new URL
     */
    public void addNewNotifiedURL(String url){
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL_NOTIFIED, url);

        open().insertWithOnConflict(TABLE_NOTIFY_ARTICLES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public long getNumberOfRow(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NOTIFY_ARTICLES);
        db.close();
        return count;

    }
}
