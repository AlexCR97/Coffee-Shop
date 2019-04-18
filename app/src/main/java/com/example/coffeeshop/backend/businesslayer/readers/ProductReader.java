package com.example.coffeeshop.backend.businesslayer.readers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.ProductTransaction;
import com.example.coffeeshop.backend.entitieslayer.Product;

import java.util.ArrayList;

public class ProductReader extends Reader<Product> {

    private ProductTransaction reader;

    public ProductReader(Context context) {
        super(context);
        reader = new ProductTransaction(context);
    }

    @Override
    public Product getEntityId(Object id) {
        return reader.selectId(id);
    }

    @Override
    public ArrayList<Product> getEntities() {
        return reader.selectAll();
    }
}
