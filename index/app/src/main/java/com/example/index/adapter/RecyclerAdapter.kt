package com.example.index.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.index.R
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    var titleList: ArrayList<String>,
    var descriptionofTitle: ArrayList<Int>,
    var imageList: ArrayList<Int>
) : RecyclerView.Adapter<RecyclerAdapter.FruitsViewHolder>() {

    class FruitsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.imageView4)
        var title: TextView = view.findViewById(R.id.textView3)
        var description: TextView = view.findViewById(R.id.textView4)
    }

    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FruitsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}


