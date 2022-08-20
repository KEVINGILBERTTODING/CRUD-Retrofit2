package com.example.myapplication.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Adapter.ContactAdapter;
import com.example.myapplication.Model.ContactModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.ContactInterface;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView rv_contact;
    ContactAdapter contactAdapter;
    List<ContactModel> contactModelList;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton btn_add;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        rv_contact = view.findViewById(R.id.rv_contact);
        btn_add = view.findViewById(R.id.btn_add);


        // called get contact
        getContact();



        return view;
    }

    // method for get all contact
    private void getContact() {
        ContactInterface ci = DataApi.getClient().create(ContactInterface.class);
        ci.getContact().enqueue(new Callback<List<ContactModel>>() {
            @Override
            public void onResponse(Call<List<ContactModel>> call, Response<List<ContactModel>> response) {
               
                if (response.isSuccessful()) {
                    contactModelList = response.body();
                    contactAdapter = new ContactAdapter(getContext(), contactModelList);
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rv_contact.setLayoutManager(linearLayoutManager);
                    rv_contact.setAdapter(contactAdapter);
                    rv_contact.setHasFixedSize(true);

                } else {
                    Toast.makeText(getContext(), "Failed load data", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<ContactModel>> call, Throwable t) {
                Snackbar.make(getView(), "Error no connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }
}