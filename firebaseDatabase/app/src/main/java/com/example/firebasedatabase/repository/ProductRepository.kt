package com.example.firebasedatabase.repository

import android.net.Uri
import com.example.firebasedatabase.model.ProductModel
import com.squareup.picasso.Callback
import java.net.URL

interface ProductRepository {

fun addProduct(productModel: ProductModel,callback:(Boolean,String?)->Unit)
fun uploadImage(imageName:String,imageURL: Uri,callback: (Boolean, String?) -> Unit)
fun getProduct(callback:(List<ProductModel>?,Boolean,String?)->Unit)
fun deleteData(id:String,callback: (Boolean, String?) -> Unit)
fun deleteImage(imageName:String,callback: (Boolean, String?) -> Unit)
fun updateProduct(id:String,data:MutableMap<String,Any>?,callback: (Boolean, String?) -> Unit)


}