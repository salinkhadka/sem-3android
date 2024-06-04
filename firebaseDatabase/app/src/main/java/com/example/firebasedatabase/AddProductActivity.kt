package com.example.firebasedatabase

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.databinding.ActivityAddProductBinding
import com.example.firebasedatabase.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.UUID

class AddProductActivity : AppCompatActivity() {
    lateinit var addProductBinding: ActivityAddProductBinding
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref: DatabaseReference = firebaseDatabase.reference.child("products")
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUris: Uri? = null

    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val storageRef = firebaseStorage.reference

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            activityResultLauncher.launch(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        addProductBinding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(addProductBinding.root)

        registerActivityForResult()
        addProductBinding.imageBrowse.setOnClickListener {

            Log.d("Hello","hello")

            val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                android.Manifest.permission.READ_MEDIA_IMAGES
            } else {
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            }
            if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permissions), 1)
            } else {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                activityResultLauncher.launch(intent)
            }
        }

        addProductBinding.button.setOnClickListener {
            if (imageUris != null) {
                uploadPhoto()
            } else {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun addProducts(url: String) {
        val name: String = addProductBinding.sendPName.text.toString()
        val price: Int = addProductBinding.sendPPrice.text.toString().toInt()
        val desc: String = addProductBinding.sendDesc.text.toString()
        val id = ref.push().key.toString()
        val data = ProductModel(id, name, price, desc, url)

        ref.child(id).setValue(data).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(applicationContext, "Data added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(applicationContext, it.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun registerActivityForResult() {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                val resultCode = result.resultCode
                val imageData = result.data
                if (resultCode == RESULT_OK && imageData != null) {
                    imageUris = imageData.data
                    imageUris?.let {
                        Picasso.get().load(it).into(addProductBinding.imageBrowse)
                    }
                }
            })
    }

    private fun uploadPhoto() {
        val imageName = UUID.randomUUID().toString()
        val imageRef = storageRef.child("products").child(imageName)

        imageUris?.let { uri ->
            imageRef.putFile(uri).addOnSuccessListener{

                imageRef.downloadUrl.addOnSuccessListener {down->
                    var imageUrl = down.toString()
                    addProducts(imageUrl)
                }

            }.addOnFailureListener {

            }


        }

    }
}
