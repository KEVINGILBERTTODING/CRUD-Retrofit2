package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.ContactModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.ContactInterface;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.snackbar.Snackbar;

import java.net.InterfaceAddress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateNewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateNewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNewFragment newInstance(String param1, String param2) {
        CreateNewFragment fragment = new CreateNewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ContactInterface contactInterface;
    private EditText et_name, et_number, et_address;
    private Button btn_save;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_new, container, false);

        et_name = view.findViewById(R.id.edt_name);
        et_address = view.findViewById(R.id.edt_address);
        et_number = view.findViewById(R.id.edt_number);
        btn_save = view.findViewById(R.id.btn_save);

        btn_save.setOnClickListener(view1 -> {

            // vaidation form cannot empty
            if ( et_name.getText().toString().isEmpty() || et_number.getText().toString().isEmpty()
                    || et_name.getText().toString().isEmpty()) {

                Toast.makeText(getContext(), "Field cannot empty", Toast.LENGTH_SHORT).show();


            }
            else {
                createContact();
            }
        });


        return view;
    }

    // method for create new contact

    private void createContact (){
        contactInterface = DataApi.getClient().create(ContactInterface.class);
        contactInterface.createContact(
                et_name.getText().toString(), et_address.getText().toString(),et_number.getText().toString()
                ).enqueue(new Callback<ContactModel>() {
            @Override
            public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                if (response.body().getStatus().equals("success")) {
                    Snackbar.make(getView(), "Success create new contact", Snackbar.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.popBackStack();
                } else {
                    Snackbar.make(getView(), "Failed", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContactModel> call, Throwable t) {
                Snackbar.make(getView(), "Success create new contact", Snackbar.LENGTH_SHORT).show();

            }
        });
    }
}