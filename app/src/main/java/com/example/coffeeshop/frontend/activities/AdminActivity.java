package com.example.coffeeshop.frontend.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.coffeeshop.R;
import com.example.coffeeshop.frontend.fragments.AdminProductsFragment;
import com.example.coffeeshop.frontend.fragments.AdminSalesFragment;
import com.example.coffeeshop.frontend.fragments.AdminUsersFragment;

public class AdminActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private AdminProductsFragment productsFragment = new AdminProductsFragment();
    private AdminUsersFragment usersFragment = new AdminUsersFragment();
    private AdminSalesFragment salesFragment = new AdminSalesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // init components
        navigation = findViewById(R.id.navigationAdmin);
        navigation.setOnNavigationItemSelectedListener(navigationListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerAdmin, productsFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigationAdminProducts:
                selectedFragment = productsFragment;
                break;

            case R.id.navigationAdminUsers:
                selectedFragment = usersFragment;
                break;

            case R.id.navigationAdminSales:
                selectedFragment = salesFragment;
                break;
        }

        if (selectedFragment == null)
            return false;

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerAdmin, selectedFragment).commit();
        return true;
        }
    };

}
