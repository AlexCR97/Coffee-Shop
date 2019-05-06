package com.example.coffeeshop.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.datalayer.transactions.ProductTransaction;
import com.example.coffeeshop.entitieslayer.Product;

public class ProductWriter extends Writer<Product> {

    public static int TRANSACTION_DELETE_ID_CATEGORY = 4;

    private ProductTransaction writer;

    public ProductWriter(Context context, int transaction, Product product) {
        super(context, transaction, product);

        writer = new ProductTransaction(context);
    }

    public ProductWriter(Context context, int transaction, Object updateId, Product product) {
        super(context, transaction, updateId, product);

        writer = new ProductTransaction(context);
    }

    @Override
    public boolean executeChanges() {
        if (transaction == TRANSACTION_INSERT)
            return writer.insert(entity);

        if (transaction == TRANSACTION_DELETE)
            return writer.delete(entity.getName());

        if (transaction == TRANSACTION_UPDATE)
            return writer.update(updateId, entity);

        if (transaction == TRANSACTION_DELETE_ID_CATEGORY)
            return writer.delete(entity.getName(), entity.getCategory());

        return false;
    }
}
