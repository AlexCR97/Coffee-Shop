package com.example.coffeeshop.frontend.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.backend.entitieslayer.Category;
import com.example.coffeeshop.backend.entitieslayer.Product;
import com.example.coffeeshop.frontend.activities.ViewCategoryProductsActivity;

import java.util.ArrayList;

public class AdminProductsFragment extends Fragment {

    private Button buttonNewCategory;
    private ArrayList<String> categories = new ArrayList<>();
    private ListView listViewCategories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_admin_products, container, false);

        // init components
        buttonNewCategory = view.findViewById(R.id.buttonNewCategory);
        listViewCategories = view.findViewById(R.id.listViewCategories);

        buttonNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final EditText editText = new EditText(v.getContext());
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String categoryName = editText.getText().toString();
                                categories.add(categoryName);
                                Toast.makeText(v.getContext(), "Categoria '" + categoryName + "' anadida!", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Nueva categoria")
                        .setPositiveButton("Añadir", dialogListener)
                        .setNegativeButton("Cancelar", dialogListener)
                        .setView(editText)
                        .show();
            }
        });

        for (int i = 1; i <= 10; i++)
            categories.add("Category " + i);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, categories);
        listViewCategories.setAdapter(adapter);
        listViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = categories.get(position);

                Intent intent = new Intent(getActivity(), ViewCategoryProductsActivity.class);
                intent.putExtra(ICategoryContract.FIELD_NAME, category);

                startActivity(intent);

            }
        });

        return view;
    }
}
