package com.example.coffeeshop.userlayer.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coffeeshop.R;
import com.example.coffeeshop.businesslayer.readers.CartReader;
import com.example.coffeeshop.businesslayer.readers.UserReader;
import com.example.coffeeshop.businesslayer.writers.CartWriter;
import com.example.coffeeshop.datalayer.contracts.IUserContract;
import com.example.coffeeshop.entitieslayer.Cart;
import com.example.coffeeshop.entitieslayer.Sales;
import com.example.coffeeshop.userlayer.components.CartListAdapter;

import java.util.ArrayList;

public class UserCartFragment extends Fragment {

    private ListView listViewCart;
    private Button buttonConfirmSale;

    private ArrayList<Cart> cart;
    private String userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_cart, container, false);

        // init components
        listViewCart = view.findViewById(R.id.listViewCart);
        listViewCart.setOnItemLongClickListener(listViewCartListener);

        buttonConfirmSale = view.findViewById(R.id.buttonConfirmSale);
        buttonConfirmSale.setOnClickListener(buttonConfirmSaleListener);

        // init user
        userName = getArguments().getString(IUserContract.FIELD_USER_NAME);

        // fill list
        fillCart(view.getContext());

        return view;
    }

    private void fillCart(Context context) {
        CartReader reader = new CartReader(context);
        cart = reader.getEntitiesWithUser(userName);

        if (cart == null)
            cart = new ArrayList<>();

        CartListAdapter adapter = new CartListAdapter(getActivity(), R.layout.list_item_cart, cart);
        listViewCart.setAdapter(adapter);
    }

    private ListView.OnItemLongClickListener listViewCartListener = new ListView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
            final Cart cartItem = cart.get(position);

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {

                            CartWriter writer = new CartWriter(view.getContext(), CartWriter.TRANSACTION_DELETE_USERNAME_PRODUCT, cartItem);

                            boolean success = writer.executeChanges();
                            if (!success) {
                                Toast.makeText(view.getContext(), "No se pudo quitar el producto del carrito :(", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            fillCart(view.getContext());
                            break;
                        }

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Quitar '" + cartItem.getProduct() + "' del carrito?")
                    .setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .show();

            return false;
        }
    };

    private Button.OnClickListener buttonConfirmSaleListener = new Button.OnClickListener() {
        @Override
        public void onClick(final View v) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {
                            Toast.makeText(v.getContext(), "Compra realizada :D", Toast.LENGTH_SHORT).show();
                        }

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Realizar compra?")
                    .setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .show();
        }
    };

}

