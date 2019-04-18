package com.example.coffeeshop.backend.datalayer.contracts;

import com.example.coffeeshop.backend.entitieslayer.Sales;

public interface ISalesContract extends IContract<Sales> {

    String TABLE = "Sales";
    String FIELD_SALE_NUMBER = "saleNumber";
    String FIELD_USER_NAME = "userName";
    String FIELD_PRODUCT = "product";
    String FIELD_PRICE = "price";
    String FIELD_AMOUNT = "amount";
    String FIELD_DATE = "date";

}
