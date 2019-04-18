package com.example.coffeeshop.backend.datalayer.contracts;

import com.example.coffeeshop.backend.entitieslayer.Cart;

public interface ICartContract extends IContract<Cart> {

    String TABLE = "Cart";
    String FIELD_USER_NAME = "userName";
    String FIELD_PRODUCT = "product";
    String FIELD_AMOUNT = "amount";

}
