package com.example.coffeeshop.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coffeeshop.datalayer.connections.CoffeeShopConnection;

public class Transaction {

    private CoffeeShopConnection connection;
    private SQLiteDatabase db;

    protected Object[] parametersWrite;
    protected String[] parametersRead;

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
            Log.d("db", "============================================================================");
            Log.d("db", "============================================================================");
            Log.d("db", "EXECUTE QUERY: " + query);
            if (parametersWrite == null)
                Log.d("db", "ParametersWrite: NULL");
            else {
                Log.d("db", "ParametersWrite:");
                for (Object parameter : parametersWrite) Log.d("db", parameter.toString());
            }
            Log.d("db", "============================================================================");
            Log.d("db", "============================================================================");

            db = connection.getWritableDatabase();
            db.execSQL(query, parametersWrite);
            return true;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    protected Cursor executeSelect(String query) {
        try {
            Log.d("db", "============================================================================");
            Log.d("db", "============================================================================");
            Log.d("db", "SELECT QUERY: " + query);
            if (parametersRead == null)
                Log.d("db", "ParametersRead: NULL");
            else {
                Log.d("db", "ParametersRead:");
                for (String parameter : parametersRead) Log.d("db", parameter);
            }
            Log.d("db", "============================================================================");
            Log.d("db", "============================================================================");

            db = connection.getReadableDatabase();
            return db.rawQuery(query, parametersRead);
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
