package com.example.coffeeshop.datalayer.contracts;

import com.example.coffeeshop.entitieslayer.Cart;

import java.util.ArrayList;

public interface ICartContract extends IContract<Cart> {

    String TABLE = "Cart";
    String FIELD_USER_NAME = "userName";
    String FIELD_PRODUCT = "product";
    String FIELD_AMOUNT = "amount";

    ArrayList<Cart> selectAllUserName(String userName);
    boolean deleteUserNameProduct(String userName, String product);
}
