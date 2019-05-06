package com.example.coffeeshop.userlayer.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.readers.ProductReader;
import com.example.coffeeshop.entitieslayer.Cart;
import com.example.coffeeshop.entitieslayer.Product;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends ArrayAdapter<Cart> {

    static class DataHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        NumericUpDown numericUpDown;
        TextView textViewSubTotal;
    }

    private Context context;
    private int layoutResourceId;
    private ArrayList<Cart> cart;

    public CartListAdapter(Context context, int resource, List<Cart> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.cart = (ArrayList<Cart>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.textViewProductName = convertView.findViewById(R.id.textViewCartItemProductName);
            holder.textViewProductPrice = convertView.findViewById(R.id.textViewCartItemProductPrice);
            holder.numericUpDown = convertView.findViewById(R.id.numericUpDownCartItem);
            holder.textViewSubTotal = convertView.findViewById(R.id.textViewCartItemSubTotal);

            holder.numericUpDown.setMinValue(1);

            convertView.setTag(holder);
        } else
            holder = (DataHolder) convertView.getTag();

        // Calculate totals
        Cart cartItem = cart.get(position);
        ProductReader reader = new ProductReader(convertView.getContext());
        Product product = reader.getEntityId(cartItem.getProduct());

        float subTotal = product.getPrice();
        float total = subTotal * cartItem.getAmount();

        String subTotalString = "$" + subTotal;
        String totalString = "$" + total;

        // fill data
        holder.textViewProductName.setText(cartItem.getProduct());
        holder.textViewProductPrice.setText(subTotalString);
        holder.numericUpDown.setValue(cartItem.getAmount());
        holder.textViewSubTotal.setText(totalString);

        return convertView;
    }

}
