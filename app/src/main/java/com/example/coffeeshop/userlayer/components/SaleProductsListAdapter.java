package com.example.coffeeshop.userlayer.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.entitieslayer.Sales;

import java.util.ArrayList;
import java.util.List;

public class SaleProductsListAdapter extends ArrayAdapter<Sales> {

    static class DataHolder {
        public TextView textViewProduct;
        public TextView textViewAmount;
        public TextView textViewPrice;
    }

    private Context context;
    private int layoutResourceId;
    private ArrayList<Sales> sales;

    public SaleProductsListAdapter(Context context, int resource, List<Sales> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.sales = (ArrayList<Sales>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.textViewProduct = convertView.findViewById(R.id.textViewListItemSaleProductName);
            holder.textViewAmount = convertView.findViewById(R.id.textViewListItemSaleProductAmount);
            holder.textViewPrice = convertView.findViewById(R.id.textViewListItemSaleProductPrice);

            convertView.setTag(holder);
        } else
            holder = (DataHolder) convertView.getTag();

        Sales sale = sales.get(position);

        float total = 0;

        holder.textViewProduct.setText(sale.getProduct());
        holder.textViewAmount.setText(String.valueOf(sale.getAmount()));
        holder.textViewPrice.setText(String.valueOf(sale.getPrice()));

        return convertView;
    }

}
