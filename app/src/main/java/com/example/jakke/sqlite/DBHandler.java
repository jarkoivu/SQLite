package com.example.jakke.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jakke on 8.8.2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    // Keeps track of the database version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "_productName";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                            COLUMN_ID  + " INREGER PRIMARY KEY AUTOINCREMENT " +
                            COLUMN_PRODUCTNAME + "TEXT" + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);

    }

    // Add a new row to the database
    public void addProduct(Products products){
        ContentValues values = new ContentValues();
        values.put("COLUMN_PRODUCTNAME", products.get_productName());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("TABLE_PRODUCTS", null, values);
        sqLiteDatabase.close();
    }

    // Delete a row from the database
    public void deleteProduct(String productName){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }
    // Print out the database as a string
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 ";
        // Cursor to point the results
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        // Move to the first of the results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("productName")) != null) {
                dbString += c.getString(c.getColumnIndex("productName"));
                dbString += "\n";
            }
        }
        sqLiteDatabase.close();
        return dbString;
    }
}
























