package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Fragment.EditContactFragment;
import com.example.myapplication.Model.ContactModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.ContactInterface;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    List<ContactModel> contactModelList;

    // this is constructor
    public ContactAdapter (Context context, List<ContactModel> contactModelList) {
        this.context = context;
        this.contactModelList = contactModelList;
    }



    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(contactModelList.get(position).getName());
        holder.tv_address.setText(contactModelList.get(position).getAddress());
        holder.tv_number.setText(contactModelList.get(position).getPhone_number());

        holder.cardView.setOnLongClickListener(view -> {
            // do something

            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_menu);
            dialog.setTitle("Menu");
            final Button btn_edit = dialog.findViewById(R.id.btn_mnu_edit);
            final Button btn_delete = dialog.findViewById(R.id.btn_mnu_delete);

            btn_edit.setOnClickListener(view1 -> {
                Fragment fragment = new EditContactFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", contactModelList.get(position).getName());
                bundle.putString("id", contactModelList.get(position).getId());
                bundle.putString("address", contactModelList.get(position).getAddress());
                bundle.putString("phone_number", contactModelList.get(position).getPhone_number());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                dialog.dismiss();


            });

            btn_delete.setOnClickListener(view1 -> {
                ContactInterface contactInterface = DataApi.getClient().create(ContactInterface.class);
                contactInterface.deleteContact(contactModelList.get(position).getId()).enqueue(new Callback<ContactModel>() {
                    @Override
                    public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                        if (response.body().getStatus().equals("success")) {
                            contactModelList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, contactModelList.size());
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ContactModel> call, Throwable t) {

                    }
                });

            });


            dialog.show();


            return true;
        });

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_address, tv_number;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_number = itemView.findViewById(R.id.tv_number);
            cardView = itemView.findViewById(R.id.card_view);




        }

    }
}
