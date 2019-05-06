package com.example.coffeeshop.userlayer.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.entitieslayer.Product;
import com.example.coffeeshop.entitieslayer.Sales;

import java.util.ArrayList;
import java.util.List;

public class SalesListAdapter extends ArrayAdapter<Sales> {

    static class DataHolder {
        public TextView textViewSaleNumber;
        public TextView textViewUserName;
        public TextView textViewDate;
        public TextView textViewTotal;
    }

    private Context context;
    private int layoutResourceId;
    private ArrayList<Sales> sales;

    public SalesListAdapter(Context context, int resource, List<Sales> objects) {
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
            holder.textViewSaleNumber = convertView.findViewById(R.id.textViewListItemSaleNumber);
            holder.textViewUserName = convertView.findViewById(R.id.textViewListItemSaleUserName);
            holder.textViewDate = convertView.findViewById(R.id.textViewListItemSaleDate);
            holder.textViewTotal = convertView.findViewById(R.id.textViewListItemSaleTotal);

            convertView.setTag(holder);
        } else
            holder = (DataHolder) convertView.getTag();

        Sales sale = sales.get(position);

        float total = 0;

        holder.textViewSaleNumber.setText(String.valueOf(sale.getSaleNumber()));
        holder.textViewUserName.setText(sale.getUserName());
        holder.textViewDate.setText(sale.getDate());
        holder.textViewTotal.setText("$" + String.valueOf(total));

        return convertView;
    }

}
