package com.example.coffeeshop.userlayer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.Utils;
import com.example.coffeeshop.businesslayer.readers.UserReader;
import com.example.coffeeshop.businesslayer.writers.UserWriter;
import com.example.coffeeshop.datalayer.contracts.IUserContract;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private CheckBox checkBoxAdmin;
    private Button buttonLogin;
    private TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init components
        editTextUserName = findViewById(R.id.editTextUserNameLogin);
        editTextPassword = findViewById(R.id.editTextPasswordLogin);

        checkBoxAdmin = findViewById(R.id.checkBoxAdmin);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(buttonLoginListener);

        textViewSignUp = findViewById(R.id.textViewSignUp);
        textViewSignUp.setOnClickListener(textViewSignUpListener);
    }

    private Button.OnClickListener buttonLoginListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userName = editTextUserName.getText().toString();
            String password = editTextPassword.getText().toString();

            if (Utils.isStringEmpty(userName) || Utils.isStringEmpty(password))
                return;

            if (checkBoxAdmin.isChecked())
                loginAdmin(userName, password);
            else
                loginUser(userName, password);
        }
    };

    private void loginAdmin(String userName, String password) {
        if (!userName.equals("admin")) {
            Toast.makeText(this, "Nombre de usuario de administrador incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals("pass")) {
            Toast.makeText(this, "Contrasena de administrador incorrecta", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginUser(String userName, String password) {
        UserReader reader = new UserReader(this);

        boolean success = reader.checkExistance(userName, password);
        if (!success) {
            Toast.makeText(this, "Usuario o contrasena incorrecta :(", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(IUserContract.FIELD_USER_NAME, userName);
        startActivity(intent);
        finish();
    }

    private TextView.OnClickListener textViewSignUpListener = new TextView.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
