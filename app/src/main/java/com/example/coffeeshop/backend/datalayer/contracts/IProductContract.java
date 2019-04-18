package com.example.coffeeshop.backend.datalayer.contracts;

import com.example.coffeeshop.backend.entitieslayer.Product;

public interface IProductContract extends IContract<Product> {

    String TABLE = "Product";
    String FIELD_NAME = "name";
    String FIELD_CATEGORY = "category";
    String FIELD_PRICE = "price";
    String FIELD_UNIT = "unit";

}
