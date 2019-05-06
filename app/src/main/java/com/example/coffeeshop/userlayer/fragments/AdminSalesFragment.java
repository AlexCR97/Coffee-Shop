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
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.datalayer.contracts.ISalesContract;
import com.example.coffeeshop.entitieslayer.Sales;
import com.example.coffeeshop.userlayer.activities.ViewSaleActivity;
import com.example.coffeeshop.userlayer.components.SalesListAdapter;

import java.util.ArrayList;

public class AdminSalesFragment extends Fragment {

    private ListView listViewSales;
    private ArrayList<Sales> sales = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_sales, container, false);

        // init components
        for (int i = 1; i <= 10; i++)
            sales.add(new Sales(
                    i,
                    "User " + i,
                    "Product " + i,
                    15.50f,
                    i,
                    "13/11/97"
            ));

        SalesListAdapter adapter = new SalesListAdapter(view.getContext(), R.layout.list_item_sale, sales);

        listViewSales = view.findViewById(R.id.listViewSales);
        listViewSales.setAdapter(adapter);
        listViewSales.setOnItemClickListener(listViewSalesListener);

        return view;
    }

    private ListView.OnItemClickListener listViewSalesListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Sales sale = sales.get(position);

            Intent intent = new Intent(getActivity(), ViewSaleActivity.class);
            intent.putExtra(ISalesContract.FIELD_SALE_NUMBER, String.valueOf(sale.getSaleNumber()));
            intent.putExtra(ISalesContract.FIELD_USER_NAME, sale.getUserName());
            intent.putExtra(ISalesContract.FIELD_DATE, sale.getDate());

            startActivity(intent);
        }
    };

}
