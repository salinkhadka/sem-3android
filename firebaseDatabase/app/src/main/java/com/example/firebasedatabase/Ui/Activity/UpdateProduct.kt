package com.example.firebasedatabase.Ui.Activity

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
import com.example.firebasedatabase.R
import com.example.firebasedatabase.Utils.ImageUtils
import com.example.firebasedatabase.ViewModel.ProductViewModel
import com.example.firebasedatabase.databinding.ActivityUpdateProductBinding
import com.example.firebasedatabase.model.ProductModel
import com.example.firebasedatabase.repository.ProductRepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class UpdateProduct : AppCompatActivity() {
    lateinit var upbinding: ActivityUpdateProductBinding
    var imageUris: Uri? = null
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref = firebaseDatabase.reference.child("products")
    var id = ""
    var imageName = ""
    lateinit var imageUtils: ImageUtils
    lateinit var productViewModel: ProductViewModel


    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


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

        upbinding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(upbinding.root)
        var repo = ProductRepositoryImpl()
        productViewModel = ProductViewModel(repo)

        imageUtils = ImageUtils(this)
        imageUtils.registerActivity {
            imageUris = it
            Picasso.get().load(it).into(upbinding.imageUpdate)
        }


        var product: ProductModel? = intent.getParcelableExtra("product")
        imageName = product?.imageName.toString()
        id = product?.id.toString()
        upbinding.updateName.setText(product?.name)
        upbinding.updatePrice.setText(product?.price.toString())
        upbinding.updateDesc.setText(product?.description)
        Picasso.get().load(product?.url).into(upbinding.imageUpdate)
        upbinding.button.setOnClickListener {
            if (imageUris != null) {

                uploadPhoto()

            } else {
                updateProducts(product?.url.toString())

            }

            finish()

        }
        upbinding.imageUpdate.setOnClickListener {
            imageUtils.launchGallery(this@UpdateProduct)

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun uploadPhoto() {
        imageUris?.let {
            productViewModel.uploadImage(imageName, it) { sucess, imageUrl ->
                if (sucess) {
                    updateProducts(imageUrl.toString())
                }
            }

        }


    }


    fun updateProducts(url: String) {
        var updatedName: String = upbinding.updateName.text.toString()
        var updatedPrice: Int = upbinding.updatePrice.text.toString().toInt()
        var updatedDesc: String = upbinding.updateDesc.text.toString()

        var data = mutableMapOf<String, Any>()
        data["name"] = updatedName
        data["price"] = updatedPrice
        data["description"] = updatedDesc
        data["url"] = url

        productViewModel.UpdateProduct(id, data) { sucess, message ->
            if (sucess) {
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            }

        }
    }
}