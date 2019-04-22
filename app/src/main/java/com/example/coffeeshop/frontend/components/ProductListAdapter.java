package com.example.coffeeshop.frontend.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.entitieslayer.Product;
import com.example.coffeeshop.backend.entitieslayer.User;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    static class DataHolder {
        public TextView textViewProductName;
        public TextView getTextViewProductPrice;
    }

    private Context context;
    private int layoutResourceId;
    private ArrayList<Product> products;

    public ProductListAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.products = (ArrayList<Product>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.textViewProductName = convertView.findViewById(R.id.textViewListItemProductName);
            holder.getTextViewProductPrice = convertView.findViewById(R.id.textViewListItemProductPrice);

            convertView.setTag(holder);
        } else
            holder = (DataHolder) convertView.getTag();

        Product product = products.get(position);
        holder.textViewProductName.setText(product.getName());
        holder.getTextViewProductPrice.setText(String.valueOf(product.getPrice()));

        return convertView;
    }

}
