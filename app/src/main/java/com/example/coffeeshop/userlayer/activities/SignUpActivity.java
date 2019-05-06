package com.example.coffeeshop.userlayer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.Utils;
import com.example.coffeeshop.businesslayer.writers.UserWriter;
import com.example.coffeeshop.entitieslayer.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;
    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Init components
        editTextUserName = findViewById(R.id.editTextUserNameSignUp);
        editTextEmail = findViewById(R.id.editTextEmailSignUp);
        editTextPassword = findViewById(R.id.editTextPasswordSignUp);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPasswordSignUp);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(buttonSignUpListener);

        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(textViewLoginListener);
    }

    private View.OnClickListener buttonSignUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userName = editTextUserName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();

            if (
                Utils.isStringEmpty(userName) ||
                Utils.isStringEmpty(email) ||
                Utils.isStringEmpty(password) ||
                Utils.isStringEmpty(confirmPassword)
            ) {
                Toast.makeText(SignUpActivity.this, "Debes de llenar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUpActivity.this, "Vuelve a confirmar tu contrasena", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(userName, email, password);
            UserWriter writer = new UserWriter(SignUpActivity.this, UserWriter.TRANSACTION_INSERT, user);

            boolean success = writer.executeChanges();
            if (!success) {
                Toast.makeText(SignUpActivity.this, "No se pudo registrar el usuario :(", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(SignUpActivity.this, "Usuario registrado :D", Toast.LENGTH_SHORT).show();
        }
    };

    private TextView.OnClickListener textViewLoginListener = new TextView.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
