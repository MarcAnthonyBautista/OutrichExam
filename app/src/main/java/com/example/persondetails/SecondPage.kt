package com.example.persondetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.persondetails.`interface`.MockApi
import com.example.persondetails.adapter.PersonAdapter
import com.example.persondetails.model.PersonModel
import com.example.persondetails.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second_page.*
import kotlinx.android.synthetic.main.common_item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SecondPage : AppCompatActivity() {
    lateinit var mockApi2:MockApi
    val key_name=""
    var friends = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)
        this@SecondPage.setTitle("Person Details")

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mockApi2=retrofit.create(MockApi::class.java)

        //getBundle
        val bundle: Bundle? = intent.extras
        val key_name: String? = intent.getStringExtra("KEY_NAME")

        getPostName(key_name)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater=menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_friends -> {
                val intent = Intent(this, ThirdPage::class.java)
                intent.putExtra("KEY_FRIENDS",Arrays.toString(friends))
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getPostName(key_name:String?){
        var call=mockApi2.getPost(key_name)
        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if(response.isSuccessful){
                    var posts=response.body()

                        textView.setText(posts?.name)
                        textView3.setText(posts?.email)
                        textView5.setText(posts?.mobile_number)
                        textView7.setText(posts?.address)
                        friends= posts?.friends!!
                    return
                }
                else{
                    Log.d("mensahe", "--"+response.code())
                }
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                t.message?.let { Log.d("message", it) }
            }
        })


    }
}