package com.example.myapplication.Util;

import com.example.myapplication.Model.ContactModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactInterface  {

    // for get all contact
    @GET("get_contact.php")
    Call<List<ContactModel>> getContact();

}
