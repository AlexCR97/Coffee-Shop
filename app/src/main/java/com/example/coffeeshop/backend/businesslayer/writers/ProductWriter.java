package com.example.coffeeshop.backend.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.ProductTransaction;
import com.example.coffeeshop.backend.entitieslayer.Product;

public class ProductWriter extends Writer<Product> {

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

        return false;
    }
}
