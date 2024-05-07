package com.example.classtask.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.classtask.R

class adap (
    var NumList: ArrayList<Int>
): RecyclerView.Adapter<adap.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var data:TextView=view.findViewById(R.id.textView)
        var card:CardView=view.findViewById(R.id.cv)


    }








    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adap.ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.activity_adap,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: adap.ViewHolder, position: Int) {
        holder.data.text=NumList[position].toString()
    }

    override fun getItemCount(): Int {
        return NumList.size
    }
}