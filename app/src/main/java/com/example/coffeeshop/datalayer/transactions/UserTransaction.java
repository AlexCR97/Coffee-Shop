package com.example.coffeeshop.datalayer.transactions;

import android.content.Context;
import android.database.Cursor;

import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.User;

import java.util.ArrayList;

public class UserTransaction extends Transaction implements IUserContract {

    private String queryCheckExistance;

    public UserTransaction(Context context) {
        super(context);

        queryInsert = String.format("insert into %s values(?, ?, ?)", IUserContract.TABLE);
        queryDelete = String.format("delete from %s where %s = ?", IUserContract.TABLE, IUserContract.FIELD_USER_NAME);
        queryUpdate = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?",
                IUserContract.TABLE,
                IUserContract.FIELD_USER_NAME,
                IUserContract.FIELD_EMAIL,
                IUserContract.FIELD_PASSWORD,
                IUserContract.FIELD_USER_NAME
        );
        querySelectAll = "select * from " + IUserContract.TABLE;
        querySelectId = String.format("select * from %s where %s = ?", IUserContract.TABLE, IUserContract.FIELD_USER_NAME);

        queryCheckExistance = String.format("select * from %s where %s = ? and %s = ?",
                IUserContract.TABLE,
                IUserContract.FIELD_USER_NAME,
                IUserContract.FIELD_PASSWORD
        );
    }

    @Override
    public boolean insert(User user) {
        parametersWrite = new Object[] {
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
        };
        return executeQuery(queryInsert);
    }

    @Override
    public boolean delete(Object id) {
        parametersWrite = new Object[] {id};
        return executeQuery(queryDelete);
    }

    @Override
    public boolean update(Object id, User user) {
        parametersWrite = new Object[] {
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                id,
        };
        return executeQuery(queryUpdate);
    }

    @Override
    public ArrayList<User> selectAll() {
        parametersRead = null;
        ArrayList<User> users = new ArrayList<>();

        Cursor c = executeSelect(querySelectAll);
        if (!c.moveToFirst())
            return null;

        do {
            users.add(new User(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2)
            ));
        } while (c.moveToNext());

        return users;
    }

    @Override
    public User selectId(Object id) {
        parametersRead = new String[] { String.valueOf(id) };

        Cursor c = executeSelect(querySelectId);
        if (!c.moveToFirst())
            return null;

        return new User(
                c.getString(0),
                c.getString(1),
                c.getString(2)
        );
    }

    @Override
    public boolean checkExistance(String userName, String password) {
        parametersRead = new String[] { userName, password };
        Cursor c = executeSelect(queryCheckExistance);
        return c.moveToFirst();
    }

}
