package com.example.coffeeshop.backend.datalayer.connections;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CoffeeShopConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CoffeeShop";
    public static final int VERSION = 1;

    private final String CREATE_TABLE_USERS = "";
    private final String CREATE_TABLE_CATEGORIES = "";
    private final String CREATE_TABLE_PRODUCTS = "";
    private final String CREATE_TABLE_CART = "";
    private final String CREATE_TABLE_SALES = "";
    private final String CREATE_TABLE_ADMINS = "";

    public CoffeeShopConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();

            db.execSQL(CREATE_TABLE_USERS);
            db.execSQL(CREATE_TABLE_CATEGORIES);
            db.execSQL(CREATE_TABLE_PRODUCTS);
            db.execSQL(CREATE_TABLE_CART);
            db.execSQL(CREATE_TABLE_SALES);
            db.execSQL(CREATE_TABLE_ADMINS);

            db.setTransactionSuccessful();
            db.endTransaction();
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
