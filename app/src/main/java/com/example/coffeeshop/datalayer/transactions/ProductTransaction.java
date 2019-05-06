package com.example.coffeeshop.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;

import com.example.coffeeshop.datalayer.contracts.IProductContract;
import com.example.coffeeshop.entitieslayer.Product;

import java.util.ArrayList;

public class ProductTransaction extends Transaction implements IProductContract {

    private String queryDeleteIdCategory;
    private String querySelectAllWithCategory;

    public ProductTransaction(Context context) {
        super(context);

        queryInsert = String.format("insert into %s values(?, ?, ?, ?)", IProductContract.TABLE);
        queryDelete = String.format("delete from %s where %s = ?", IProductContract.TABLE, IProductContract.FIELD_NAME);
        queryUpdate = String.format("update %s set %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
                IProductContract.TABLE,
                IProductContract.FIELD_NAME,
                IProductContract.FIELD_CATEGORY,
                IProductContract.FIELD_PRICE,
                IProductContract.FIELD_UNIT,
                IProductContract.FIELD_NAME
        );
        querySelectAll = "select * from " + IProductContract.TABLE;
        querySelectId = String.format("select * from %s where %s = ?", IProductContract.TABLE, IProductContract.FIELD_NAME);

        queryDeleteIdCategory = String.format("delete from %s where %s = ? and %s = ?",
                IProductContract.TABLE,
                IProductContract.FIELD_NAME,
                IProductContract.FIELD_CATEGORY
        );
        querySelectAllWithCategory = String.format("select * from %s where %s = ?", IProductContract.TABLE, IProductContract.FIELD_CATEGORY);
    }

    @Override
    public boolean insert(Product product) {
        parametersWrite = new Object[] {
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getUnit(),
        };
        return executeQuery(queryInsert);
    }

    @Override
    public boolean delete(Object id) {
        parametersWrite = new Object[] {id};
        return executeQuery(queryDelete);
    }

    @Override
    public boolean update(Object id, Product product) {
        parametersWrite = new Object[] {
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getUnit(),
                id
        };
        return executeQuery(queryUpdate);
    }

    @Override
    public ArrayList<Product> selectAll() {
        parametersRead = null;
        ArrayList<Product> products = new ArrayList<>();

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        do {
            products.add(new Product(
                    c.getString(0),
                    c.getString(1),
                    Float.parseFloat(c.getString(2)),
                    c.getString(3)
            ));
        } while (c.moveToNext());

        return products;
    }

    @Override
    public Product selectId(Object id) {
        parametersRead = null;

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        return new Product(
                c.getString(0),
                c.getString(1),
                Float.parseFloat(c.getString(2)),
                c.getString(3)
        );
    }

    @Override
    public boolean delete(Object id, String category) {
        parametersWrite = new Object[] {
                id,
                category
        };
        return executeQuery(queryDeleteIdCategory);
    }

    @Override
    public ArrayList<Product> selectAllWithCategory(String category) {
        parametersRead = new String[] { category };

        ArrayList<Product> products = new ArrayList<>();

        Cursor c = executeSelect(querySelectAllWithCategory);
        if (!c.moveToFirst())
            return null;

        do {
            products.add(new Product(
                    c.getString(0),
                    c.getString(1),
                    Float.parseFloat(c.getString(2)),
                    c.getString(3)
            ));
        } while (c.moveToNext());

        return products;
    }

}
