package com.example.coffeeshop.backend.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.CartTransaction;
import com.example.coffeeshop.backend.entitieslayer.Cart;

public class CartWriter extends Writer<Cart> {

    private CartTransaction writer;

    public CartWriter(Context context, int transaction, Cart cart) {
        super(context, transaction, cart);

        writer = new CartTransaction(context);
    }

    public CartWriter(Context context, int transaction, Object updateId, Cart cart) {
        super(context, transaction, updateId, cart);

        writer = new CartTransaction(context);
    }


    @Override
    public boolean executeChanges() {
        if (transaction == TRANSACTION_INSERT)
            return writer.insert(entity);

        if (transaction == TRANSACTION_DELETE)
            return writer.delete(entity.getUserName());

        if (transaction == TRANSACTION_UPDATE)
            return writer.update(updateId, entity);

        return false;
    }
}
