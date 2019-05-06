package com.example.coffeeshop.userlayer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.datalayer.contracts.ISalesContract;
import com.example.coffeeshop.entitieslayer.Sales;
import com.example.coffeeshop.userlayer.components.SaleProductsListAdapter;

import java.util.ArrayList;

public class ViewSaleActivity extends AppCompatActivity {

    private TextView textViewSaleNumber;
    private TextView textViewUserName;
    private TextView textViewDate;
    private TextView textViewTotal;
    private ListView listViewSaleProducts;

    private ArrayList<Sales> sales = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sale);

        // init components
        textViewSaleNumber = findViewById(R.id.textViewViewSaleNumber);
        textViewUserName = findViewById(R.id.textViewViewSaleUserName);
        textViewDate = findViewById(R.id.textViewViewSaleDate);
        textViewTotal = findViewById(R.id.textViewViewSaleTotal);
        listViewSaleProducts = findViewById(R.id.listViewSaleProducts);

        // fill data
        String saleNumber = getIntent().getStringExtra(ISalesContract.FIELD_SALE_NUMBER);
        String userName = getIntent().getStringExtra(ISalesContract.FIELD_USER_NAME);
        String date = getIntent().getStringExtra(ISalesContract.FIELD_DATE);

        textViewSaleNumber.setText(saleNumber);
        textViewUserName.setText(userName);
        textViewDate.setText(date);

        // fill list
        for (int i = 1; i <= 10; i++)
            sales.add(new Sales(
                    i,
                    "User " + 1,
                    "Product " + i,
                    15.5f,
                    i,
                    "13/11/97"
            ));

        SaleProductsListAdapter adapter = new SaleProductsListAdapter(this, R.layout.list_item_sale_product, sales);

        listViewSaleProducts.setAdapter(adapter);
    }

}
