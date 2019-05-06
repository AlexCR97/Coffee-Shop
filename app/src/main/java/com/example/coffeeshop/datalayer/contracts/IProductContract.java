package com.example.coffeeshop.datalayer.contracts;

import com.example.coffeeshop.entitieslayer.Product;

import java.util.ArrayList;

public interface IProductContract extends IContract<Product> {

    String TABLE = "Product";
    String FIELD_NAME = "name";
    String FIELD_CATEGORY = "category";
    String FIELD_PRICE = "price";
    String FIELD_UNIT = "unit";

    boolean delete(Object id, String category);

    ArrayList<Product> selectAllWithCategory(String category);

}
