package com.example.coffeeshop.backend.businesslayer.readers;

import android.content.Context;

import com.example.coffeeshop.backend.datalayer.transactions.UserTransaction;
import com.example.coffeeshop.backend.entitieslayer.User;

import java.util.ArrayList;

public class UserReader extends Reader<User> {

    private UserTransaction reader;

    public UserReader(Context context) {
        super(context);
        reader = new UserTransaction(context);
    }

    @Override
    public User getEntityId(Object id) {
        return reader.selectId(id);
    }

    @Override
    public ArrayList<User> getEntities() {
        return reader.selectAll();
    }
}
