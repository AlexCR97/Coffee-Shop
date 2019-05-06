package com.example.coffeeshop.userlayer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.coffeeshop.R;
import com.example.coffeeshop.Utils;
import com.example.coffeeshop.businesslayer.readers.CategoryReader;
import com.example.coffeeshop.datalayer.contracts.ICategoryContract;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.userlayer.activities.UserViewCategoryProductsActivity;

import java.util.ArrayList;

public class UserDashboardFragment extends Fragment {

    private LinearLayout dashboard;
    private String userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_dashboard, container, false);

        // init components
        dashboard = view.findViewById(R.id.linearLayoutDashboard);

        // init dashboard
        CategoryReader reader = new CategoryReader(view.getContext());
        ArrayList<String> categories = reader.getCategoryNames();

        if (categories == null)
            categories = new ArrayList<>();

        for (String name : categories) {
            Button categoryButton = createCategoryButton(view, name);
            addCategoryButton(categoryButton);
        }

        // init user
        userName = getArguments().getString(IUserContract.FIELD_USER_NAME);

        return view;
    }

    private void addCategoryButton(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserViewCategoryProductsActivity.class);
                intent.putExtra(IUserContract.FIELD_USER_NAME, userName);
                intent.putExtra(ICategoryContract.FIELD_NAME, button.getText().toString());

                startActivity(intent);
            }
        });
        dashboard.addView(button);
    }

    private Button createCategoryButton(View view, String category) {
        int margin = 8;
        int paddingH = 5;
        int paddingV = 70;
        int size = 15;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.topMargin = Utils.dp2px(view.getContext(), margin);

        Button button = new Button(view.getContext());
        button.setBackgroundColor(Utils.getRandomColor());
        button.setText(category);
        button.setAllCaps(false);
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setTextSize(Utils.sp2px(view.getContext(), size));
        button.setPadding(
                Utils.dp2px(view.getContext(), paddingH),
                Utils.dp2px(view.getContext(), paddingV),
                Utils.dp2px(view.getContext(), paddingH),
                Utils.dp2px(view.getContext(), paddingV));
        button.setLayoutParams(layoutParams);

        return button;
    }

}
