package com.example.firebasedatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.databinding.ActivityUpdateProductBinding
import com.example.firebasedatabase.model.ProductModel

class UpdateProduct : AppCompatActivity() {
    lateinit var upbinding:ActivityUpdateProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        upbinding=ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(upbinding.root)

        var product:ProductModel?=intent.getParcelableExtra("product")

        upbinding.updateName.setText(product?.name)
        upbinding.updatePrice.setText(product?.price.toString())
        upbinding.updateDesc.setText(product?.description)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}