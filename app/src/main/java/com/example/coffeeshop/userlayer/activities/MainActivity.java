package com.example.coffeeshop.userlayer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.coffeeshop.R;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.User;
import com.example.coffeeshop.userlayer.fragments.UserAccountFragment;
import com.example.coffeeshop.userlayer.fragments.UserCartFragment;
import com.example.coffeeshop.userlayer.fragments.UserDashboardFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private UserAccountFragment accountFragment = new UserAccountFragment();
    private UserDashboardFragment dashboardFragment = new UserDashboardFragment();
    private UserCartFragment cartFragment = new UserCartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init components
        navigation = findViewById(R.id.navigationUser);
        navigation.setOnNavigationItemSelectedListener(navigationListener);

        // init user
        String userName = getIntent().getStringExtra(IUserContract.FIELD_USER_NAME);

        Bundle bundleDashboard = new Bundle();
        bundleDashboard.putString(IUserContract.FIELD_USER_NAME, userName);
        dashboardFragment.setArguments(bundleDashboard);

        Bundle bundleCart = new Bundle();
        bundleCart.putString(IUserContract.FIELD_USER_NAME, userName);
        cartFragment.setArguments(bundleCart);

        Bundle bundleAccount = new Bundle();
        bundleAccount.putString(IUserContract.FIELD_USER_NAME, userName);
        accountFragment.setArguments(bundleAccount);
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
