package com.example.coffeeshop.backend.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.CategoryTransaction;
import com.example.coffeeshop.backend.entitieslayer.Category;

public class CategoryWriter extends Writer<Category> {

    private CategoryTransaction writer;

    public CategoryWriter(Context context, int transaction, Category category) {
        super(context, transaction, category);

        writer = new CategoryTransaction(context);
    }

    public CategoryWriter(Context context, int transaction, Object updateId, Category category) {
        super(context, transaction, updateId, category);

        writer = new CategoryTransaction(context);
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
