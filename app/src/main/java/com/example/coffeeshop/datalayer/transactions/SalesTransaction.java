package com.example.coffeeshop.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;

import com.example.coffeeshop.datalayer.contracts.ISalesContract;
import com.example.coffeeshop.entitieslayer.Sales;

import java.util.ArrayList;

public class SalesTransaction extends Transaction implements ISalesContract {

    public SalesTransaction(Context context) {
        super(context);

        queryInsert = String.format("insert into %s values(?, ?, ?, ?, ?, ?)", ISalesContract.TABLE);
        queryDelete = String.format("delete from %s where %s = ?", ISalesContract.TABLE, ISalesContract.FIELD_SALE_NUMBER);
        queryUpdate = String.format("update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
                ISalesContract.TABLE,
                ISalesContract.FIELD_SALE_NUMBER,
                ISalesContract.FIELD_USER_NAME,
                ISalesContract.FIELD_PRODUCT,
                ISalesContract.FIELD_PRICE,
                ISalesContract.FIELD_AMOUNT,
                ISalesContract.FIELD_DATE
        );
        querySelectAll = "select * from " + ISalesContract.TABLE;
        querySelectId = String.format("select * from %s where %s = ?", ISalesContract.TABLE, ISalesContract.FIELD_SALE_NUMBER);
    }

    @Override
    public boolean insert(Sales sales) {
        parametersWrite = new Object[] {
                sales.getSaleNumber(),
                sales.getUserName(),
                sales.getProduct(),
                sales.getPrice(),
                sales.getAmount(),
                sales.getDate(),
        };
        return executeQuery(queryInsert);
    }

    @Override
    public boolean delete(Object id) {
        parametersWrite = new Object[] {id};
        return executeQuery(queryDelete);
    }

    @Override
    public boolean update(Object id, Sales sales) {
        parametersWrite = new Object[] {
                sales.getSaleNumber(),
                sales.getUserName(),
                sales.getProduct(),
                sales.getPrice(),
                sales.getAmount(),
                sales.getDate(),
                id
        };
        return executeQuery(queryUpdate);
    }

    @Override
    public ArrayList<Sales> selectAll() {
        parametersRead = null;
        ArrayList<Sales> sales = new ArrayList<>();

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        do {
            sales.add(new Sales(
                    Integer.parseInt(c.getString(0)),
                    c.getString(1),
                    c.getString(2),
                    Float.parseFloat(c.getString(3)),
                    Integer.parseInt(c.getString(4)),
                    c.getString(5)
            ));
        } while (c.moveToNext());

        return sales;
    }

    @Override
    public Sales selectId(Object id) {
        parametersRead = null;

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        return new Sales(
                Integer.parseInt(c.getString(0)),
                c.getString(1),
                c.getString(2),
                Float.parseFloat(c.getString(3)),
                Integer.parseInt(c.getString(4)),
                c.getString(5)
        );
    }
}
