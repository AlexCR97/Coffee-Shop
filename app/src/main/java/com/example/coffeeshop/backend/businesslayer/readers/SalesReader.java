package com.example.coffeeshop.backend.businesslayer.readers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.SalesTransaction;
import com.example.coffeeshop.backend.entitieslayer.Sales;

import java.util.ArrayList;

public class SalesReader extends Reader<Sales> {

    private SalesTransaction reader;

    public SalesReader(Context context) {
        super(context);
        reader = new SalesTransaction(context);
    }

    @Override
    public Sales getEntityId(Object id) {
        return reader.selectId(id);
    }

    @Override
    public ArrayList<Sales> getEntities() {
        return reader.selectAll();
    }
}
