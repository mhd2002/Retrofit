package com.example.retrofit

import com.example.retrofit.Model.Books
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("books")
    fun getbooks():Call<Books>


}