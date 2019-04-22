package com.example.coffeeshop.frontend.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.IUserContract;
import com.example.coffeeshop.backend.entitieslayer.User;
import com.example.coffeeshop.frontend.fragments.UserAccountFragment;
import com.example.coffeeshop.frontend.fragments.UserCartFragment;
import com.example.coffeeshop.frontend.fragments.UserDashboardFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private UserAccountFragment accountFragment = new UserAccountFragment();
    private UserDashboardFragment dashboardFragment = new UserDashboardFragment();
    private UserCartFragment cartFragment = new UserCartFragment();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init components
        navigation = findViewById(R.id.navigationUser);
        navigation.setOnNavigationItemSelectedListener(navigationListener);

        // init user
        user = new User("Alejandro", "ale@live.com", "123");

        Bundle bundle = new Bundle();
        bundle.putString(IUserContract.FIELD_USER_NAME, user.getUserName());
        bundle.putString(IUserContract.FIELD_EMAIL, user.getEmail());

        accountFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerUser, accountFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigationUserAccount:
                selectedFragment = accountFragment;
                break;

            case R.id.navigationUserDashboard:
                selectedFragment = dashboardFragment;
                break;

            case R.id.navigationUserCart:
                selectedFragment = cartFragment;
                break;
        }

        if (selectedFragment == null)
            return false;

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerUser, selectedFragment).commit();
        return true;
    }};

}
