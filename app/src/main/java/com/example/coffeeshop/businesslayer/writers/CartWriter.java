package com.example.coffeeshop.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.datalayer.transactions.CartTransaction;
import com.example.coffeeshop.entitieslayer.Cart;

public class CartWriter extends Writer<Cart> {

    public static final int TRANSACTION_DELETE_USERNAME_PRODUCT = 4;

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

        if (transaction == TRANSACTION_DELETE_USERNAME_PRODUCT)
            return writer.deleteUserNameProduct(entity.getUserName(), entity.getProduct());

        return false;
    }
}
