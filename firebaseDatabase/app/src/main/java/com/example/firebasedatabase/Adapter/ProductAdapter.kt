package com.example.firebasedatabase.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedatabase.R
import com.example.firebasedatabase.Ui.Activity.UpdateProduct
import com.example.firebasedatabase.model.ProductModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ProductAdapter(var context: Context, var data: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productName: TextView = view.findViewById(R.id.CardText)
        var productPrice: TextView = view.findViewById(R.id.CardPrice)
        var productDesc: TextView = view.findViewById(R.id.Desc)
        var btnEdit: TextView = view.findViewById(R.id.EditUpd)
        var progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        var imgView: ImageView = view.findViewById(R.id.imageViewDisplay)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.sample, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productName.text = data[position].name
        holder.productPrice.text = data[position].price.toString()
        holder.productDesc.text = data[position].description
        var image=data[position].url

        Picasso.get().load(image).into(holder.imgView,object :Callback{
            override fun onSuccess() {
                holder.progressBar.visibility=View.INVISIBLE
            }

            override fun onError(e: Exception?) {
                Toast.makeText(context,e?.localizedMessage,Toast.LENGTH_LONG).show()



            }

        })


        holder.btnEdit.setOnClickListener {
            var intent = Intent(context, UpdateProduct()::class.java)
            intent.putExtra("product", data[position])
            context.startActivity(intent)

        }
    }
    fun getProductID(position: Int) : String{
        return data[position].id
    }  fun getImageName(position: Int) : String{
        return data[position].imageName
    }
    fun updateData(products:List<ProductModel>){
        data.clear()
        Log.d("called from adapter",products[0].name)
        data.addAll(products)
        notifyDataSetChanged()
    }

}