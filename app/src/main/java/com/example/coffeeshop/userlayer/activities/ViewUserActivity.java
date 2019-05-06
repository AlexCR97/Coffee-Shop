package com.example.coffeeshop.userlayer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.writers.UserWriter;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.User;

public class ViewUserActivity extends AppCompatActivity {

    private TextView textViewUserName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private Button buttonDeleteUser;

    private String userName;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // init components
        textViewUserName = findViewById(R.id.textViewViewUserName);
        textViewEmail = findViewById(R.id.textViewViewEmail);
        textViewPassword = findViewById(R.id.textViewViewPassword);

        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);
        buttonDeleteUser.setOnClickListener(buttonDeleteUserListener);

        // fill data
        userName = getIntent().getStringExtra(IUserContract.FIELD_USER_NAME);
        email = getIntent().getStringExtra(IUserContract.FIELD_EMAIL);
        password = getIntent().getStringExtra(IUserContract.FIELD_PASSWORD);

        textViewUserName.setText(userName);
        textViewEmail.setText(email);
        textViewPassword.setText(password);
    }

    private View.OnClickListener buttonDeleteUserListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {

                            User user = new User(userName);
                            UserWriter writer = new UserWriter(v.getContext(), UserWriter.TRANSACTION_DELETE, user);

                            boolean success = writer.executeChanges();
                            if (!success) {
                                Toast.makeText(v.getContext(), "No se pudo eliminar el usuario :(", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(v.getContext(), "Usuario eliminado :D", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        }

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
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
    };

}
