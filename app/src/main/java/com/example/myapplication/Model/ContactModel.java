package com.example.myapplication.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContactModel implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;
    @SerializedName("phone_number")
    String phone_number;
    @SerializedName("status")
    String status;
    public ContactModel (String id, String name, String address, String phone_number, String status){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
