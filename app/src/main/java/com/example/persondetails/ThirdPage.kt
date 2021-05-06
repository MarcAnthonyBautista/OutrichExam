package com.example.persondetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.persondetails.`interface`.MockApi
import kotlinx.android.synthetic.main.activity_third_page.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ThirdPage : AppCompatActivity() {
    var friends = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_page)
        this@ThirdPage.setTitle("Friend List")

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //getBundle

        val bundle: Bundle? = intent.extras
        friends = intent.getStringExtra( "KEY_FRIENDS").toString()
        list_view_friends.setText(friends.substring(1,friends.lastIndex).replace(",","\n"))

    }

}