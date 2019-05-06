package com.example.coffeeshop.userlayer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.readers.ProductReader;
import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.datalayer.contracts.IProductContract;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.Product;
import com.example.coffeeshop.userlayer.components.ProductGridAdapter;

import java.util.ArrayList;

public class UserViewCategoryProductsActivity extends AppCompatActivity {

    private TextView textViewCategory;
    private GridView gridView;
    private ArrayList<Product> products;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_category_products);

        // init components
        String category = getIntent().getStringExtra(ICategoryContract.FIELD_NAME);
        userName = getIntent().getStringExtra(IUserContract.FIELD_USER_NAME);

        textViewCategory = findViewById(R.id.textViewCategoryNameUser);
        textViewCategory.setText(category);

        gridView = findViewById(R.id.gridViewProducts);
        gridView.setOnItemClickListener(gridViewListener);

        // fill grid
        ProductReader reader = new ProductReader(this);
        products = reader.getEntitiesWithCategory(category);

        if (products == null)
            products = new ArrayList<>();

        ProductGridAdapter adapter = new ProductGridAdapter(this, R.layout.grid_item_product, products);
        gridView.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener gridViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Product product = products.get(position);

            Intent intent = new Intent(UserViewCategoryProductsActivity.this, UserViewProductActivity.class);
            intent.putExtra(IUserContract.FIELD_USER_NAME, userName);
            intent.putExtra(IProductContract.FIELD_NAME, product.getName());
            intent.putExtra(IProductContract.FIELD_CATEGORY, product.getCategory());
            intent.putExtra(IProductContract.FIELD_PRICE, String.valueOf(product.getPrice()));
            intent.putExtra(IProductContract.FIELD_UNIT, product.getUnit());

            startActivity(intent);
        }
    };

}
