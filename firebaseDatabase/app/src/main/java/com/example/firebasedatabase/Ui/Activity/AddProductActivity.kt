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
import androidx.lifecycle.ViewModel
import com.example.firebasedatabase.R
import com.example.firebasedatabase.Utils.ImageUtils
import com.example.firebasedatabase.ViewModel.ProductViewModel
import com.example.firebasedatabase.databinding.ActivityAddProductBinding
import com.example.firebasedatabase.model.ProductModel
import com.example.firebasedatabase.repository.ProductRepository
import com.example.firebasedatabase.repository.ProductRepositoryImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.UUID

class AddProductActivity : AppCompatActivity() {
    lateinit var addProductBinding: ActivityAddProductBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUris: Uri? = null
    lateinit var imageUtils: ImageUtils
    lateinit var productViewModel: ProductViewModel


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

        imageUtils = ImageUtils(this)
        imageUtils.registerActivity { url ->
            url.let {
                imageUris = url
                Picasso.get().load(url).into(addProductBinding.imageBrowse)
            }


        }
        var repo = ProductRepositoryImpl()
        productViewModel = ProductViewModel(repo)
        addProductBinding.imageBrowse.setOnClickListener {

            imageUtils.launchGallery(this)
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

    private fun addProducts(url: String, imageName: String) {
        val name: String = addProductBinding.sendPName.text.toString()
        val price: Int = addProductBinding.sendPPrice.text.toString().toInt()
        val desc: String = addProductBinding.sendDesc.text.toString()

        var data = ProductModel("", name, price, desc, url)
        productViewModel.addProduct(data) { sucess, message ->
            if (sucess) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }

    }


    private fun uploadPhoto() {
        val imageName = UUID.randomUUID().toString()
        imageUris?.let {
            productViewModel.uploadImage(imageName,it) { sucess, imageUrl ->
                if (sucess) {
                    addProducts(imageUrl.toString(),imageName.toString())
                }
            }
        }


    }

}

