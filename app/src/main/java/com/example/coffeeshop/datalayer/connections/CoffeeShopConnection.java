package com.example.coffeeshop.datalayer.connections;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.coffeeshop.datalayer.contracts.ICartContract;
import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.datalayer.contracts.IProductContract;
import com.example.coffeeshop.datalayer.contracts.ISalesContract;
import com.example.coffeeshop.datalayer.contracts.IUserContract;

public class CoffeeShopConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CoffeeShop";
    public static final int VERSION = 1;

    private final String CREATE_TABLE_USERS = String.format(
            "create table %s(" +
                    "%s text primary key, " +
                    "%s text, " +
                    "%s text" +
            ")",
            IUserContract.TABLE, IUserContract.FIELD_USER_NAME, IUserContract.FIELD_EMAIL, IUserContract.FIELD_PASSWORD
    );

    private final String CREATE_TABLE_CATEGORIES = String.format(
            "create table %s(" +
                    "%s text primary key" +
            ")",
            ICategoryContract.TABLE, ICategoryContract.FIELD_NAME
    );

    private final String CREATE_TABLE_PRODUCTS = String.format(
            "create table %s(" +
                    "%s text primary key, " +
                    "%s text, " +
                    "%s real, " +
                    "%s text" +
            ")",
            IProductContract.TABLE, IProductContract.FIELD_NAME, IProductContract.FIELD_CATEGORY,
            IProductContract.FIELD_PRICE, IProductContract.FIELD_UNIT
    );

    private final String CREATE_TABLE_CART = String.format(
            "create table %s(" +
                    "%s text, " +
                    "%s text, " +
                    "%s integer" +
            ")",
            ICartContract.TABLE, ICartContract.FIELD_USER_NAME, ICartContract.FIELD_PRODUCT, ICartContract.FIELD_AMOUNT
    );

    private final String CREATE_TABLE_SALES = String.format(
            "create table %s(" +
                    "%s integer, " +
                    "%s text, " +
                    "%s text, " +
                    "%s real, " +
                    "%s integer, " +
                    "%s text" +
            ")",
            ISalesContract.TABLE, ISalesContract.FIELD_SALE_NUMBER, ISalesContract.FIELD_USER_NAME, ISalesContract.FIELD_PRODUCT,
            ISalesContract.FIELD_PRICE, ISalesContract.FIELD_AMOUNT, ISalesContract.FIELD_DATE
    );

    public CoffeeShopConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        Log.d("db", "===========================================================================");
        Log.d("db", "===========================================================================");
        Log.d("db", "CREATE TABLE QUERIES");
        Log.d("db", CREATE_TABLE_CART);
        Log.d("db", CREATE_TABLE_CATEGORIES);
        Log.d("db", CREATE_TABLE_PRODUCTS);
        Log.d("db", CREATE_TABLE_SALES);
        Log.d("db", CREATE_TABLE_USERS);
        Log.d("db", "===========================================================================");
        Log.d("db", "===========================================================================");
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
