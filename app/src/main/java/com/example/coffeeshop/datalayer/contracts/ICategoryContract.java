package com.example.coffeeshop.datalayer.contracts;

import com.example.coffeeshop.entitieslayer.Category;

import java.util.ArrayList;

public interface ICategoryContract extends IContract<Category> {

    String TABLE = "Category";
    String FIELD_NAME = "name";

    ArrayList<String> selectAllAsString();
}
