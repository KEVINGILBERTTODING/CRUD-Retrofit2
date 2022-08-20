package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Model.ContactModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.ContactInterface;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditContactFragment extends Fragment {

    private EditText et_name, et_number, et_address;
    private Button btn_save;
    String id, name, number, address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_contact, container, false);


        et_name = view.findViewById(R.id.edt_name);
        et_address = view.findViewById(R.id.edt_address);
        et_number = view.findViewById(R.id.edt_number);
        btn_save = view.findViewById(R.id.btn_save);

        // get data from adapter using bundle
        id = getArguments().getString("id");
        name = getArguments().getString("name");
        number = getArguments().getString("phone_number");
        address = getArguments().getString("address");


        et_name.setText(name);
        et_address.setText(address);
        et_number.setText(number);

        btn_save.setOnClickListener(view1 -> {
           if (et_name.getText().toString().isEmpty() || et_address.getText().toString().isEmpty() || et_number.getText().toString().isEmpty()){
               Snackbar.make(view1, "Please fill all fields", Snackbar.LENGTH_SHORT).show();
           } else {
               updateContact(id, et_name.getText().toString(), et_address.getText().toString(), et_number.getText().toString());
           }
        });





        return view;
    }

    // method for update data
    private void updateContact (String id, String name, String address, String number) {
        ContactInterface contactInterface = DataApi.getClient().create(ContactInterface.class);
        contactInterface.editContact(id, name, address, number)
                .enqueue(new Callback<ContactModel>() {
                    @Override
                    public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {

                        if (response.body().getStatus().equals("success")) {
                            Snackbar.make(getView(), "Update Success", Snackbar.LENGTH_SHORT).show();
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.popBackStack();
                        } else {
                            // do something
                            Snackbar.make(getView(), "Failed", Snackbar.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ContactModel> call, Throwable t) {
                        Snackbar.make(getView(), "Error no connection", Snackbar.LENGTH_SHORT).show();

                    }
                });
    }


}