package com.example.coffeeshop.userlayer.fragments;

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

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.readers.UserReader;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.User;
import com.example.coffeeshop.userlayer.activities.ViewUserActivity;
import com.example.coffeeshop.userlayer.components.UserListAdapter;

import java.util.ArrayList;

public class AdminUsersFragment extends Fragment {

    private ListView listViewUsers;
    private ArrayList<User> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_users, container, false);

        // init components
        listViewUsers = view.findViewById(R.id.listViewUsers);
        listViewUsers.setOnItemClickListener(listViewUserListener);

        // fill list
        onResume();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        UserReader reader = new UserReader(getContext());
        users = reader.getEntities();

        if (users == null)
            users = new ArrayList<>();

        UserListAdapter adapter = new UserListAdapter(getContext(), R.layout.list_item_user, users);
        listViewUsers.setAdapter(adapter);
    }

    private ListView.OnItemClickListener listViewUserListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            User user = users.get(position);

            Intent intent = new Intent(getActivity(), ViewUserActivity.class);
            intent.putExtra(IUserContract.FIELD_USER_NAME, user.getUserName());
            intent.putExtra(IUserContract.FIELD_EMAIL, user.getEmail());
            intent.putExtra(IUserContract.FIELD_PASSWORD, user.getPassword());

            startActivity(intent);
        }
    };

}
