package com.example.coffeeshop.backend.businesslayer.readers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.CartTransaction;
import com.example.coffeeshop.backend.entitieslayer.Cart;

import java.util.ArrayList;

public class CartReader extends Reader<Cart> {

    private CartTransaction reader;

    public CartReader(Context context) {
        super(context);
        reader = new CartTransaction(context);
    }

    @Override
    public Cart getEntityId(Object id) {
        return reader.selectId(id);
    }

    @Override
    public ArrayList<Cart> getEntities() {
        return reader.selectAll();
    }
}
