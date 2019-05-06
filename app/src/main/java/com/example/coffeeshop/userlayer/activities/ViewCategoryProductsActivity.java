package com.example.coffeeshop.userlayer.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuPresenter;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.readers.ProductReader;
import com.example.coffeeshop.businesslayer.writers.ProductWriter;
import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.datalayer.contracts.IProductContract;
import com.example.coffeeshop.entitieslayer.Product;
import com.example.coffeeshop.userlayer.components.ProductListAdapter;

import java.util.ArrayList;

public class ViewCategoryProductsActivity extends AppCompatActivity {

    private Button buttonNewProduct;
    private TextView textViewCategory;
    private ListView listViewProducts;

    private ArrayList<Product> products;
    private String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category_products);

        // init components
        category = getIntent().getStringExtra(ICategoryContract.FIELD_NAME);

        textViewCategory = findViewById(R.id.textViewCategoryName);
        textViewCategory.setText(category);

        buttonNewProduct = findViewById(R.id.buttonNewProduct);
        buttonNewProduct.setOnClickListener(buttonNewProductListener);

        listViewProducts = findViewById(R.id.listViewProducts);
        listViewProducts.setOnItemClickListener(listViewProductsListener);

        // fill list view
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ProductReader reader = new ProductReader(this);
        products = reader.getEntitiesWithCategory(category);

        if (products == null)
            products = new ArrayList<>();

        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.list_item_product, products);
        listViewProducts.setAdapter(adapter);
    }

    private Button.OnClickListener buttonNewProductListener = new Button.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final EditText editTextProductName = new EditText(v.getContext());
            final EditText editTextProductPrice = new EditText(v.getContext());
            final EditText editTextProductUnit = new EditText(v.getContext());

            editTextProductName.setInputType(InputType.TYPE_CLASS_TEXT);
            editTextProductPrice.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editTextProductUnit.setInputType(InputType.TYPE_CLASS_TEXT);

            editTextProductName.setHint("Nombre del producto");
            editTextProductPrice.setHint("Precio");
            editTextProductUnit.setHint("Unidad (1kg, 4 piezas, etc.)");

            LinearLayout layout = new LinearLayout(v.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(editTextProductName);
            layout.addView(editTextProductPrice);
            layout.addView(editTextProductUnit);

            DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {
                            String productName = editTextProductName.getText().toString();
                            String category = textViewCategory.getText().toString();
                            String price = editTextProductPrice.getText().toString();
                            String unit = editTextProductUnit.getText().toString();

                            if (productName.equals("") || category.equals("") || price.equals("") || unit.equals(""))
                                break;

                            Product product = new Product(productName, category, Float.parseFloat(price), unit);
                            ProductWriter writer = new ProductWriter(v.getContext(), ProductWriter.TRANSACTION_INSERT, product);

                            boolean success = writer.executeChanges();
                            if (!success) {
                                Toast.makeText(v.getContext(), "No se pudo agregar el producto :(", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            products.add(product);
                            Toast.makeText(v.getContext(), "Producto agregado :D", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Nuevo producto")
                    .setPositiveButton("Añadir", dialogListener)
                    .setNegativeButton("Cancelar", dialogListener)
                    .setView(layout)
                    .show();
        }
    };

    private ListView.OnItemClickListener listViewProductsListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Product product = products.get(position);

            Intent intent = new Intent(ViewCategoryProductsActivity.this, ViewProductActivity.class);
            intent.putExtra(IProductContract.FIELD_NAME, product.getName());
            intent.putExtra(IProductContract.FIELD_CATEGORY, product.getCategory());
            intent.putExtra(IProductContract.FIELD_PRICE, String.valueOf(product.getPrice()));
            intent.putExtra(IProductContract.FIELD_UNIT, product.getUnit());

            startActivity(intent);
        }
    };

}
