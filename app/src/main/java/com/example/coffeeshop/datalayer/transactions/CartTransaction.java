package com.example.coffeeshop.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;

import com.example.coffeeshop.datalayer.contracts.ICartContract;
import com.example.coffeeshop.entitieslayer.Cart;

import java.util.ArrayList;

public class CartTransaction extends Transaction implements ICartContract {

    private String querySelectAllUserName;
    private String queryDeleteUserNameProduct;

    public CartTransaction(Context context) {
        super(context);

        queryInsert = String.format("insert into %s values(?, ?, ?)", ICartContract.TABLE);
        queryDelete = String.format("delete from %s where %s = ?", ICartContract.TABLE, ICartContract.FIELD_USER_NAME);
        queryUpdate = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?",
                ICartContract.TABLE,
                ICartContract.FIELD_USER_NAME,
                ICartContract.FIELD_PRODUCT,
                ICartContract.FIELD_AMOUNT,
                ICartContract.FIELD_USER_NAME
        );
        querySelectAll = "select * from " + ICartContract.TABLE;
        querySelectId = String.format("select * from %s where %s = ?", ICartContract.TABLE, ICartContract.FIELD_USER_NAME);

        querySelectAllUserName = String.format("select * from %s where %s = ?",
                ICartContract.TABLE, ICartContract.FIELD_USER_NAME
        );

        queryDeleteUserNameProduct = String.format("delete from %s where %s = ? and %s = ?",
                ICartContract.TABLE,
                ICartContract.FIELD_USER_NAME,
                ICartContract.FIELD_PRODUCT
        );
    }

    @Override
    public boolean insert(Cart cart) {
        parametersWrite = new Object[] {
                cart.getUserName(),
                cart.getProduct(),
                cart.getAmount(),
        };
        return executeQuery(queryInsert);
    }

    @Override
    public boolean delete(Object id) {
        parametersWrite = new Object[] {id};
        return executeQuery(queryDelete);
    }

    @Override
    public boolean update(Object id, Cart cart) {
        parametersWrite = new Object[] {
                cart.getUserName(),
                cart.getProduct(),
                cart.getAmount(),
                id,
        };
        return executeQuery(queryUpdate);
    }

    @Override
    public ArrayList<Cart> selectAll() {
        parametersRead = null;
        ArrayList<Cart> cart = new ArrayList<>();

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        do {
            cart.add(new Cart(
                    c.getString(0),
                    c.getString(1),
                    Integer.parseInt(c.getString(2))
            ));
        } while (c.moveToNext());

        return cart;
    }

    @Override
    public Cart selectId(Object id) {
        parametersRead = null;

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        return new Cart(
                c.getString(0),
                c.getString(1),
                Integer.parseInt(c.getString(2))
        );
    }

    @Override
    public ArrayList<Cart> selectAllUserName(String userName) {
        parametersRead = new String[] {userName};
        ArrayList<Cart> cart = new ArrayList<>();

        Cursor c = executeSelect(querySelectAllUserName);
        if (!c.moveToFirst())
            return null;

        do {
            cart.add(new Cart(
                    c.getString(0),
                    c.getString(1),
                    Integer.parseInt(c.getString(2))
            ));
        } while (c.moveToNext());

        return cart;
    }

    @Override
    public boolean deleteUserNameProduct(String userName, String product) {
        parametersWrite = new Object[] {userName, product};
        return executeQuery(queryDeleteUserNameProduct);
    }
}
