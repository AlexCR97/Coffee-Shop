package com.example.coffeeshop.backend.datalayer.contracts;

import com.example.coffeeshop.backend.entitieslayer.User;

public interface IUserContract extends IContract<User> {

    String TABLE = "User";
    String FIELD_USER_NAME = "userName";
    String FIELD_EMAIL = "email";
    String FIELD_PASSWORD = "password";

}
