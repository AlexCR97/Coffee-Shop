package com.example.coffeeshop.businesslayer.writers;

import android.content.Context;

public abstract class Writer<Entity> {

    public static int TRANSACTION_INSERT = 1;
    public static int TRANSACTION_DELETE = 2;
    public static int TRANSACTION_UPDATE = 3;

    protected int transaction;
    protected Object updateId;
    protected Entity entity;

    public Writer(Context context, int transaction, Entity entity) {
        this.transaction = transaction;
        this.entity = entity;
    }

    public Writer(Context context, int transaction, Object updateId, Entity entity) {
        this.transaction = transaction;
        this.updateId = updateId;
        this.entity = entity;
    }

    public abstract boolean executeChanges();

}
