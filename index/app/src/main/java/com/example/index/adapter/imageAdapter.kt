package com.example.index.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.index.R

class imageAdapter(var nameList: ArrayList<String>, var imageList: ArrayList<Int>):BaseAdapter() {
    override fun getCount(): Int {
        return nameList.size;
    }

    override fun getItem(position: Int): Any? {
        return null;
    }

    override fun getItemId(position: Int): Long {
        return 0;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View = LayoutInflater.from(parent?.context)
            .inflate(R.layout.sample_grid_design_1, parent, false);
        var image: ImageView = view.findViewById(R.id.imageView2)
        var label: TextView = view.findViewById(R.id.textView2)

        label.text=nameList[position]
        image.setImageResource(imageList[position])


        return view;

    }



}