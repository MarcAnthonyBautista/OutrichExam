package com.example.persondetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.persondetails.R
import com.example.persondetails.model.PersonModel
import kotlinx.android.synthetic.main.common_item_list.view.*

class PersonAdapter(var mContext: Context, var resource:Int, var items:List<PersonModel>):ArrayAdapter<PersonModel>(mContext,resource,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var layoutInflater = LayoutInflater.from(mContext)
        var view=layoutInflater.inflate(resource,null)

        var name:TextView=view.findViewById(R.id.tv_name)
        var email:TextView=view.findViewById(R.id.tv_email)

        var items:PersonModel = items[position]
        name.text= items.name
        email.text= items.email


        return view
    }
}