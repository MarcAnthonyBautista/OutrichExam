package com.example.persondetails.`interface`

import com.example.persondetails.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MockApi {

    @GET("/posts")
    fun getPost():Call<List<Post>>

    @GET("/posts/{name}")
    fun getPost(@Path("name")name:String?):Call<Post>


}