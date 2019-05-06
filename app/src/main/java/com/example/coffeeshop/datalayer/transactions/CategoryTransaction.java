package com.example.coffeeshop.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;

import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.entitieslayer.Category;

import java.util.ArrayList;

public class CategoryTransaction extends Transaction implements ICategoryContract {

    public CategoryTransaction(Context context) {
        super(context);

        queryInsert = String.format("insert into %s values(?)", ICategoryContract.TABLE);
        queryDelete = String.format("delete from %s where %s = ?", ICategoryContract.TABLE, ICategoryContract.FIELD_NAME);
        queryUpdate = String.format("update %s set %s = ? where %s = ?",
                ICategoryContract.TABLE,
                ICategoryContract.FIELD_NAME,
                ICategoryContract.FIELD_NAME
        );
        querySelectAll = "select * from " + ICategoryContract.TABLE;
        querySelectId = String.format("select * from %s where %s = ?", ICategoryContract.TABLE, ICategoryContract.FIELD_NAME);
    }

    @Override
    public boolean insert(Category category) {
        parametersWrite = new Object[] {
                category.getName(),
        };
        return executeQuery(queryInsert);
    }

    @Override
    public boolean delete(Object id) {
        parametersWrite = new Object[] {id};
        return executeQuery(queryDelete);
    }

    @Override
    public boolean update(Object id, Category category) {
        parametersWrite = new Object[] {
                category.getName(),
                id
        };
        return executeQuery(queryInsert);
    }

    @Override
    public ArrayList<Category> selectAll() {
        parametersRead = null;
        ArrayList<Category> categories = new ArrayList<>();

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        do {
            categories.add(new Category(
                    c.getString(0)
            ));
        } while (c.moveToNext());

        return categories;
    }

    @Override
    public Category selectId(Object id) {
        parametersRead = null;

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        return new Category(
                    c.getString(0)
        );
    }

    @Override
    public ArrayList<String> selectAllAsString() {
        parametersRead = null;

        ArrayList<Category> categories = selectAll();
        if (categories == null)
            return null;

        ArrayList<String> categoryNames = new ArrayList<>();
        for (Category c : categories)
            categoryNames.add(c.getName());

        return categoryNames;
    }
}
