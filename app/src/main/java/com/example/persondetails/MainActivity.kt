package com.example.persondetails


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.persondetails.`interface`.MockApi
import com.example.persondetails.adapter.PersonAdapter
import com.example.persondetails.model.PersonModel
import com.example.persondetails.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.common_item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var mockApi:MockApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this@MainActivity.setTitle("People List")

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mockApi=retrofit.create(MockApi::class.java)
        getPost()

        //click event
        listView1.setOnItemClickListener { parent:AdapterView<*>, view: View, position:Int, id:Long ->

                    val intent = Intent(this, SecondPage::class.java)
                    intent.putExtra("KEY_NAME", view.tv_name.text)
                    startActivity(intent)
        }

    }

        //Retrofit



    fun getPost(){
        var call=mockApi.getPost()
        call.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                if(response.isSuccessful){
                    var posts=response.body()
                    var list = mutableListOf<PersonModel>()
                    for (post in posts!!){
                        list.add(PersonModel(post.name,post.email))
                    }
                    listView1.adapter=PersonAdapter(this@MainActivity,R.layout.common_item_list,list)
                    return
                }
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                t.message?.let { Log.d("message", it) }
            }
        })

    }
}