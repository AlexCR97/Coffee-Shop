package com.example.coffeeshop.frontend.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.backend.datalayer.contracts.IUserContract;
import com.example.coffeeshop.backend.entitieslayer.Product;
import com.example.coffeeshop.backend.entitieslayer.User;
import com.example.coffeeshop.frontend.activities.LoginActivity;

public class UserAccountFragment extends Fragment {

    private TextView textViewUserName;
    private TextView textViewUserEmail;
    private Button buttonEditProfile;
    private Button buttonSignOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        // init components
        textViewUserName = view.findViewById(R.id.textViewUserName);
        textViewUserEmail = view.findViewById(R.id.textViewUserEmail);
        buttonEditProfile = view.findViewById(R.id.buttonEditProfile);
        buttonSignOut = view.findViewById(R.id.buttonSignOut);

        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final EditText editTextUserName = new EditText(v.getContext());
                final EditText editTextUserEmail = new EditText(v.getContext());
                final EditText editTextUserPassword = new EditText(v.getContext());

                editTextUserName.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextUserEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                editTextUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                editTextUserName.setHint("Nuevo nombre de usuario");
                editTextUserEmail.setHint("Nuevo correo");
                editTextUserPassword.setHint("Contrasena");

                LinearLayout layout = new LinearLayout(v.getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(editTextUserName);
                layout.addView(editTextUserEmail);
                layout.addView(editTextUserPassword);

                DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String newUserName = editTextUserName.getText().toString();
                                String newEmail = editTextUserEmail.getText().toString();
                                String password = editTextUserPassword.getText().toString();

                                if (newUserName.equals("") || newEmail.equals("") || password.equals(""))
                                    break;

                                User user = new User(newUserName, newEmail, password);

                                textViewUserName.setText(newUserName);
                                textViewUserEmail.setText(newEmail);

                                Toast.makeText(v.getContext(), "Peril editado: '" + user + "'", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(v.getContext());
                builder.setTitle("Editar perfil")
                        .setPositiveButton("Editar", dialogListener)
                        .setNegativeButton("Cancelar", dialogListener)
                        .setView(layout)
                        .show();

            }
        });

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Estas seguro de que quieres cerrar sesion?")
                        .setPositiveButton("Si", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
            }
        });

        // set user data
        Bundle bundle = getArguments();
        textViewUserName.setText(bundle.getString(IUserContract.FIELD_USER_NAME));
        textViewUserEmail.setText(bundle.getString(IUserContract.FIELD_EMAIL));

        return view;
    }

}
