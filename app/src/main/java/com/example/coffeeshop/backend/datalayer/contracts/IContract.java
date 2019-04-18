package com.example.coffeeshop.backend.datalayer.contracts;

import java.util.ArrayList;

public interface IContract<Entity> {

    boolean insert(Entity entity);
    boolean delete(Object id);
    boolean update(Object id, Entity entity);
    ArrayList<Entity> selectAll();
    Entity selectId(Object id);

}
