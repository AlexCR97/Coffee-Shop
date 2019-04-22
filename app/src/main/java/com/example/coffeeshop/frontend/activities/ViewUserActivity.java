package com.example.coffeeshop.frontend.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.IUserContract;

public class ViewUserActivity extends AppCompatActivity {

    private TextView textViewUserName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private Button buttonDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // init components
        textViewUserName = findViewById(R.id.textViewViewUserName);
        textViewEmail = findViewById(R.id.textViewViewEmail);
        textViewPassword = findViewById(R.id.textViewViewPassword);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);

        String userName = getIntent().getStringExtra(IUserContract.FIELD_USER_NAME);
        String email = getIntent().getStringExtra(IUserContract.FIELD_EMAIL);
        String password = getIntent().getStringExtra(IUserContract.FIELD_PASSWORD);

        textViewUserName.setText(userName);
        textViewEmail.setText(email);
        textViewPassword.setText(password);
        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
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
                builder.setMessage("Estas seguro de que quieres eliminar a este usuario?")
                        .setPositiveButton("Si", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
            }
        });
    }

}
