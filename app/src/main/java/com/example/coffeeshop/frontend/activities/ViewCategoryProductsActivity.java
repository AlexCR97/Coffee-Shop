package com.example.coffeeshop.frontend.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.backend.datalayer.contracts.IProductContract;
import com.example.coffeeshop.backend.entitieslayer.Product;
import com.example.coffeeshop.frontend.components.ProductListAdapter;

import java.util.ArrayList;

public class ViewCategoryProductsActivity extends AppCompatActivity {

    private Button buttonNewProduct;
    private TextView textViewCategory;
    private ListView listViewProducts;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category_products);

        // init components
        textViewCategory = findViewById(R.id.textViewCategoryName);
        textViewCategory.setText(getIntent().getStringExtra(ICategoryContract.FIELD_NAME));

        buttonNewProduct = findViewById(R.id.buttonNewProduct);
        buttonNewProduct.setOnClickListener(new View.OnClickListener() {
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
                            case DialogInterface.BUTTON_POSITIVE:
                                Product product = new Product(
                                        editTextProductName.getText().toString(),
                                        textViewCategory.getText().toString(),
                                        Float.parseFloat(editTextProductPrice.getText().toString()),
                                        editTextProductUnit.getText().toString()
                                );
                                products.add(product);
                                Toast.makeText(v.getContext(), "Producto agregado: '" + product + "'", Toast.LENGTH_SHORT).show();
                                break;

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
        });

        for (int i = 1; i <= 10; i++)
            products.add(new Product(
                    "Product " + i,
                    textViewCategory.getText().toString(),
                    i,
                    "Unit " + i
            ));

        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.list_item_product, products);

        listViewProducts = findViewById(R.id.listViewProducts);
        listViewProducts.setAdapter(adapter);
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product product = products.get(position);

                Intent intent = new Intent(ViewCategoryProductsActivity.this, ViewProductActivity.class);
                intent.putExtra(IProductContract.FIELD_NAME, product.getName());
                intent.putExtra(IProductContract.FIELD_CATEGORY, product.getCategory());
                intent.putExtra(IProductContract.FIELD_PRICE, product.getPrice());
                intent.putExtra(IProductContract.FIELD_UNIT, product.getUnit());

                startActivity(intent);
            }
        });
    }
}
