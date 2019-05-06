package com.example.coffeeshop.userlayer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.writers.ProductWriter;
import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.datalayer.contracts.IProductContract;
import com.example.coffeeshop.entitieslayer.Product;

public class ViewProductActivity extends AppCompatActivity {

    private TextView textViewProductName;
    private TextView textViewProductCategory;
    private TextView textViewProductPrice;
    private TextView textViewProductUnit;
    private Button buttonDeleteProduct;

    private String name;
    private String category;
    private String price;
    private String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        // init components
        textViewProductName = findViewById(R.id.textViewViewProductName);
        textViewProductCategory = findViewById(R.id.textViewViewProductCategory);
        textViewProductPrice = findViewById(R.id.textViewViewProductPrice);
        textViewProductUnit = findViewById(R.id.textViewViewProductUnit);
        buttonDeleteProduct = findViewById(R.id.buttonDeleteProduct);
        buttonDeleteProduct.setOnClickListener(buttonDeleteProductListener);

        // fill data
        name = getIntent().getStringExtra(IProductContract.FIELD_NAME);
        category = getIntent().getStringExtra(IProductContract.FIELD_CATEGORY);
        price = getIntent().getStringExtra(IProductContract.FIELD_PRICE);
        unit = getIntent().getStringExtra(IProductContract.FIELD_UNIT);

        textViewProductName.setText(name);
        textViewProductCategory.setText(category);
        textViewProductPrice.setText(price);
        textViewProductUnit.setText(unit);
    }

    private Button.OnClickListener buttonDeleteProductListener = new Button.OnClickListener() {
        @Override
        public void onClick(final View v) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {

                            Product product = new Product(name, category);
                            ProductWriter writer = new ProductWriter(v.getContext(), ProductWriter.TRANSACTION_DELETE_ID_CATEGORY, product);

                            boolean success = writer.executeChanges();
                            if (!success) {
                                Toast.makeText(v.getContext(), "No se pudo eliminar el producto :(", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            Toast.makeText(v.getContext(), "Producto eliminado :D", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        }

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Estas seguro de que quieres eliminar el producto seleccionado?")
                    .setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .show();
        }
    };

}
