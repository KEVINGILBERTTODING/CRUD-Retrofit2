package com.example.myapplication.Util;

import com.example.myapplication.Model.ContactModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContactInterface  {

    // for get all contact
    @GET("get_contact.php")
    Call<List<ContactModel>> getContact();

    // create new contact
    @FormUrlEncoded
    @POST("create_contact.php")
    Call<ContactModel> createContact (
            @Field("name") String name,
            @Field("address") String address,
            @Field("phone_number") String phone_number
    );

    @FormUrlEncoded
    @POST("edit_contact.php")
    Call<ContactModel> editContact (
            @Field("id") String id,
            @Field("name") String name,
            @Field("address") String address,
            @Field("phone_number") String phone_number
    );

    @FormUrlEncoded
    @POST("delete_contact.php")
        Call<ContactModel> deleteContact (
                @Field("id") String id
        );

}
