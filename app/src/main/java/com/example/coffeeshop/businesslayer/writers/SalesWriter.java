package com.example.coffeeshop.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.datalayer.transactions.SalesTransaction;
import com.example.coffeeshop.entitieslayer.Sales;

public class SalesWriter extends Writer<Sales> {

    private SalesTransaction writer;

    public SalesWriter(Context context, int transaction, Sales sales) {
        super(context, transaction, sales);

        writer = new SalesTransaction(context);
    }

    public SalesWriter(Context context, int transaction, Object updateId, Sales sales) {
        super(context, transaction, updateId, sales);

        writer = new SalesTransaction(context);
    }

    @Override
    public boolean executeChanges() {
        if (transaction == TRANSACTION_INSERT)
            return writer.insert(entity);

        if (transaction == TRANSACTION_DELETE)
            return writer.delete(entity.getSaleNumber());

        if (transaction == TRANSACTION_UPDATE)
            return writer.update(updateId, entity);

        return false;
    }
}
