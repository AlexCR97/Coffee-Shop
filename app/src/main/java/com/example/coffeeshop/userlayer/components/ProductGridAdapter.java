package com.example.coffeeshop.userlayer.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.entitieslayer.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductGridAdapter extends ArrayAdapter<Product> {

    static class DataHolder {
        public TextView textViewProductName;
        public TextView textViewProductPrice;
    }

    private Context context;
    private int layoutResourceId;
    private ArrayList<Product> products;

    public ProductGridAdapter(Context context, int resource, List<Product> objects) {
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
            holder.textViewProductName = convertView.findViewById(R.id.textViewGridItemProductName);
            holder.textViewProductPrice = convertView.findViewById(R.id.textViewGridItemProductPrice);

            convertView.setTag(holder);
        } else
            holder = (DataHolder) convertView.getTag();

        Product product = products.get(position);
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText(String.valueOf(product.getPrice()));

        return convertView;
    }

}
