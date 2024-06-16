package com.example.firebasedatabase.repository

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasedatabase.model.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class ProductRepositoryImpl :ProductRepository {
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref: DatabaseReference = firebaseDatabase.reference.child("products")
    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val storageRef = firebaseStorage.reference
    override fun addProduct(productModel: ProductModel, callback: (Boolean, String?) -> Unit) {
        val id = ref.push().key.toString()
        productModel.id=id;

        ref.child(id).setValue(productModel).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true,"Your data is uploaded sucessfully")
            } else {
                callback(false,"Unable to upload")
            }
        }
    }

    override fun uploadImage(imageName: String,imageURL: Uri, callback: (Boolean,  String?) -> Unit) {



//        val imageName = UUID.randomUUID().toString()
        val imageRef = storageRef.child("products").child(imageName)

        imageURL?.let { uri ->
            imageRef.putFile(uri).addOnSuccessListener{

                imageRef.downloadUrl.addOnSuccessListener {down->
                    var imageUrl = down.toString()
                    callback(true,imageUrl)
                }

            }.addOnFailureListener {
                callback(false,"")
            }


        }
    }

    override fun getProduct( callback: (List<ProductModel>?, Boolean, String?) -> Unit) {

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var productList= mutableListOf<ProductModel>()
                for (eachData in snapshot.children) {
                    val product = eachData.getValue(ProductModel::class.java)
                    if (product != null) {
                        Log.d("data from db",product.name)
                        productList.add(product)
                    }
                }

                callback(productList,true,"data fetch sucessful")
            }

            override fun onCancelled(error: DatabaseError) {
               callback(null,false,"Unable to fetch ${error.message}")
            }
        })

    }

    override fun deleteData(id: String, callback: (Boolean, String?) -> Unit) {
        ref.child(id).removeValue()
            .addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"image deleted Sucessfully")
            }
            else{
                callback(false,"image not deleted")
            }





                }
            }


    override fun deleteImage(imageName: String, callback: (Boolean, String?) -> Unit) {
        storageRef.child("products").child(imageName).delete().addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"image deleted Sucessfully")
        }
            else{
                callback(false,"image not deleted")
            }

        }
    }

    override fun updateProduct(
        id: String,
        data: MutableMap<String, Any>?,
        callback: (Boolean, String?) -> Unit
    ) {
        data?.let {
            ref.child(id).updateChildren(it).addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true,"your data is updated Sucessfully")
                } else {
                    callback(false,"your data ISNOTT updated Sucessfully")
                }
            }
        }
    }

}