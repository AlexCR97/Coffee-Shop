package com.example.coffeeshop.businesslayer.writers;

import android.content.Context;

import com.example.coffeeshop.datalayer.transactions.UserTransaction;
import com.example.coffeeshop.entitieslayer.User;

public class UserWriter extends Writer<User> {

    private UserTransaction writer;

    public UserWriter(Context context, int transaction, User user) {
        super(context, transaction, user);

        writer = new UserTransaction(context);
    }

    public UserWriter(Context context, int transaction, Object updateId, User user) {
        super(context, transaction, updateId, user);

        writer = new UserTransaction(context);
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
