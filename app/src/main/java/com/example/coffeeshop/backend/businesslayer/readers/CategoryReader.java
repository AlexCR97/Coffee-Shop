package com.example.coffeeshop.backend.businesslayer.readers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.CategoryTransaction;
import com.example.coffeeshop.backend.entitieslayer.Category;

import java.util.ArrayList;

public class CategoryReader extends Reader<Category> {

    private CategoryTransaction reader;

    public CategoryReader(Context context) {
        super(context);
        reader = new CategoryTransaction(context);
    }

    @Override
    public Category getEntityId(Object id) {
        return reader.selectId(id);
    }

    @Override
    public ArrayList<Category> getEntities() {
        return reader.selectAll();
    }
}
