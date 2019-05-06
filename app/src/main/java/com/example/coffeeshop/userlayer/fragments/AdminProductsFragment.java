package com.example.coffeeshop.userlayer.fragments;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.coffeeshop.businesslayer.readers.CategoryReader;
import com.example.coffeeshop.businesslayer.writers.CategoryWriter;
import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.entitieslayer.Category;
import com.example.coffeeshop.userlayer.activities.ViewCategoryProductsActivity;

import java.util.ArrayList;

public class AdminProductsFragment extends Fragment {

    private Button buttonNewCategory;
    private ArrayList<String> categories;
    private ListView listViewCategories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_admin_products, container, false);

        // init button
        buttonNewCategory = view.findViewById(R.id.buttonNewCategory);
        buttonNewCategory.setOnClickListener(buttonNewCategoryListener);

        // init list view
        CategoryReader reader = new CategoryReader(view.getContext());
        categories = reader.getCategoryNames();

        if (categories == null) {
            categories = new ArrayList<>();
            for (int i = 1; i <= 5; i++)
                categories.add("Foo");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), R.layout.list_item, categories);

        listViewCategories = view.findViewById(R.id.listViewCategories);
        listViewCategories.setAdapter(adapter);
        listViewCategories.setOnItemClickListener(listViewCategoriesListener);

        return view;
    }

    private Button.OnClickListener buttonNewCategoryListener = new Button.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final EditText editText = new EditText(v.getContext());
            editText.setInputType(InputType.TYPE_CLASS_TEXT);

            DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {

                            String categoryName = editText.getText().toString();
                            if (categoryName.equals(""))
                                break;

                            Category category = new Category(categoryName);
                            CategoryWriter writer = new CategoryWriter(v.getContext(), CategoryWriter.TRANSACTION_INSERT, category);

                            boolean success = writer.executeChanges();
                            if (!success) {
                                Toast.makeText(v.getContext(), "La categoria no pudo ser anadida :(", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            categories.add(categoryName);
                            Toast.makeText(v.getContext(), "Categoria '" + categoryName + "' anadida!", Toast.LENGTH_SHORT).show();

                            break;
                        }

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
    };

    private ListView.OnItemClickListener listViewCategoriesListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = categories.get(position);

            Intent intent = new Intent(getActivity(), ViewCategoryProductsActivity.class);
            intent.putExtra(ICategoryContract.FIELD_NAME, category);

            startActivity(intent);
        }
    };

}
