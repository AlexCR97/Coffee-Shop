package com.example.coffeeshop.backend.datalayer.contracts;

import com.example.coffeeshop.backend.entitieslayer.Category;

public interface ICategoryContract extends IContract<Category> {

    String TABLE = "Category";
    String FIELD_NAME = "name";

}
