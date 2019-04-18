package com.example.coffeeshop.backend.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeeshop.backend.datalayer.connections.CoffeeShopConnection;

public class Transaction {

    private CoffeeShopConnection connection;
    private SQLiteDatabase db;

    protected Object[] parameters;
    protected String queryInsert;
    protected String queryDelete;
    protected String queryUpdate;
    protected String querySelectAll;
    protected String querySelectId;

    public Transaction(Context context) {
        connection = new CoffeeShopConnection(context, CoffeeShopConnection.DATABASE_NAME, null, CoffeeShopConnection.VERSION);
    }

    protected boolean executeQuery(String query) {
        try {
            db = connection.getWritableDatabase();
            db.execSQL(query, parameters);
            return true;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    protected Cursor executeSelect(String query) {
        try {
            db = connection.getReadableDatabase();
            return db.rawQuery(query, (String[]) parameters);
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
