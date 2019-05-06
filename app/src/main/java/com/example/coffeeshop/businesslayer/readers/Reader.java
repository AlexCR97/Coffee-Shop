package com.example.coffeeshop.businesslayer.readers;

import android.content.Context;

import java.util.ArrayList;

public abstract class Reader<Entity> {

    public Reader(Context context) {}

    public abstract Entity getEntityId(Object id);
    public abstract ArrayList<Entity> getEntities();

}
