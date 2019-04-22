package com.example.coffeeshop.frontend.components;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.entitieslayer.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {

    static class DataHolder {
        public TextView textViewUserName;
        public TextView textViewEmail;
    }

    private Context context;
    private int layoutResourceId;
    private ArrayList<User> users;

    public UserListAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.users = (ArrayList<User>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.textViewUserName = convertView.findViewById(R.id.textViewListItemUserName);
            holder.textViewEmail = convertView.findViewById(R.id.textViewListItemUserEmail);

            convertView.setTag(holder);
        } else
            holder = (DataHolder) convertView.getTag();

        User user = users.get(position);
        holder.textViewUserName.setText(user.getUserName());
        holder.textViewEmail.setText(user.getEmail());

        return convertView;
    }

}
