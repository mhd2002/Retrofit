package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retrofit.Model.Books
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofitConfig()

        val myApi: MyApi = retrofit.create(MyApi::class.java)

        myApi.getbooks().enqueue(object : retrofit2.Callback<Books> {
            override fun onResponse(call: Call<Books>, response: Response<Books>) {
                Log.e("retrofit123" , response.body().toString())
                Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_LONG)
                    .show()

            }

            override fun onFailure(call: Call<Books>, t: Throwable) {
                t.message?.let { Log.e("retrofit123", it) }
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }


        })

    }

    fun retrofitConfig() {

        retrofit = Retrofit.Builder().baseUrl("http://simple-books-api.glitch.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}