package com.example.coffeeshop.frontend.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.IProductContract;

public class ViewProductActivity extends AppCompatActivity {

    private TextView textViewProductName;
    private TextView textViewProductCategory;
    private TextView textViewProductPrice;
    private TextView textViewProductUnit;
    private Button buttonDeleteProduct;

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
        buttonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
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
        });

        // fill data
        textViewProductName.setText(getIntent().getStringExtra(IProductContract.FIELD_NAME));
        textViewProductCategory.setText(getIntent().getStringExtra(IProductContract.FIELD_CATEGORY));
        textViewProductPrice.setText(getIntent().getStringExtra(IProductContract.FIELD_PRICE));
        textViewProductUnit.setText(getIntent().getStringExtra(IProductContract.FIELD_UNIT));
    }
}
