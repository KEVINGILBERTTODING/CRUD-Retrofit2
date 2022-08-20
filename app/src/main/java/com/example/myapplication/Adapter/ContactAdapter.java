package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ContactModel;
import com.example.myapplication.R;

import java.util.List;

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

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_address, tv_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_number = itemView.findViewById(R.id.tv_number);
        }
    }
}
