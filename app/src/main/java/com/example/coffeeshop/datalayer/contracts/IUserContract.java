package com.example.coffeeshop.datalayer.contracts;

import com.example.coffeeshop.entitieslayer.User;

public interface IUserContract extends IContract<User> {

    String TABLE = "User";
    String FIELD_USER_NAME = "userName";
    String FIELD_EMAIL = "email";
    String FIELD_PASSWORD = "password";

    boolean checkExistance(String userName, String password);
}
