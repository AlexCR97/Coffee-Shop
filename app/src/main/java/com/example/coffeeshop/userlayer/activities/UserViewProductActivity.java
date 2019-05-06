package com.example.coffeeshop.userlayer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.writers.CartWriter;
import com.example.coffeeshop.datalayer.contracts.IProductContract;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.Cart;
import com.example.coffeeshop.entitieslayer.Product;
import com.example.coffeeshop.userlayer.components.NumericUpDown;

public class UserViewProductActivity extends AppCompatActivity {

    private TextView textViewProductName;
    private TextView textViewProductCategory;
    private TextView textViewProductPrice;
    private TextView textViewProductUnit;
    private NumericUpDown numericUpDown;
    private Button buttonAddToCart;
    private Button buttonBuy;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_product);

        // init components
        textViewProductName = findViewById(R.id.textViewViewProductNameUser);
        textViewProductCategory = findViewById(R.id.textViewViewProductCategoryUser);
        textViewProductPrice = findViewById(R.id.textViewViewProductPriceUser);
        textViewProductUnit = findViewById(R.id.textViewViewProductUnitUser);

        String name = getIntent().getStringExtra(IProductContract.FIELD_NAME);
        String category = getIntent().getStringExtra(IProductContract.FIELD_CATEGORY);
        String price = getIntent().getStringExtra(IProductContract.FIELD_PRICE);
        String unit = getIntent().getStringExtra(IProductContract.FIELD_UNIT);

        textViewProductName.setText(name);
        textViewProductCategory.setText(category);
        textViewProductPrice.setText(price);
        textViewProductUnit.setText(unit);

        numericUpDown = findViewById(R.id.numericUpDownViewProduct);
        numericUpDown.setMinValue(1);

        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        buttonBuy = findViewById(R.id.buttonBuy);

        buttonAddToCart.setOnClickListener(buttonAddToCartListener);
        buttonBuy.setOnClickListener(buttonBuyListener);

        // init user
        userName = getIntent().getStringExtra(IUserContract.FIELD_USER_NAME);
    }

    private Button.OnClickListener buttonAddToCartListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String productName = textViewProductName.getText().toString();
            int amount = numericUpDown.getValue();

            Cart cart = new Cart(userName, productName, amount);
            CartWriter writer = new CartWriter(v.getContext(), CartWriter.TRANSACTION_INSERT, cart);

            boolean success = writer.executeChanges();
            if (!success) {
                Toast.makeText(v.getContext(), "No se pudo anadir el producto al carrito :(", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(v.getContext(), "Producto anadido al carrito :D", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private Button.OnClickListener buttonBuyListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            Product p = new Product(
                    textViewProductName.getText().toString(),
                    textViewProductCategory.getText().toString(),
                    Float.parseFloat(textViewProductPrice.getText().toString()),
                    textViewProductUnit.getText().toString()
            );

            int amount = numericUpDown.getValue();

            Toast.makeText(v.getContext(), amount + " productos comprados: " + p.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
