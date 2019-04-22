package com.example.coffeeshop.frontend.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.IUserContract;
import com.example.coffeeshop.backend.entitieslayer.User;
import com.example.coffeeshop.frontend.activities.AdminActivity;
import com.example.coffeeshop.frontend.activities.ViewUserActivity;
import com.example.coffeeshop.frontend.components.UserListAdapter;

import java.util.ArrayList;

public class AdminUsersFragment extends Fragment {

    private ListView listViewUsers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_users, container, false);

        final ArrayList<User> users = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            users.add(new User("User" + i, "email" + i + "@live.com", "123"));

        UserListAdapter adapter = new UserListAdapter(getActivity(), R.layout.list_item_user, users);

        listViewUsers = view.findViewById(R.id.listViewUsers);
        listViewUsers.setAdapter(adapter);
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = users.get(position);

                Intent intent = new Intent(getActivity(), ViewUserActivity.class);
                intent.putExtra(IUserContract.FIELD_USER_NAME, user.getUserName());
                intent.putExtra(IUserContract.FIELD_EMAIL, user.getEmail());
                intent.putExtra(IUserContract.FIELD_PASSWORD, user.getPassword());

                startActivity(intent);
            }
        });

        return view;
    }

}
