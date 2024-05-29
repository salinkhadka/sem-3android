package com.example.firebasedatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.databinding.ActivityAddProductBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddProductActivity : AppCompatActivity() {
    lateinit var AddProductActivity:ActivityAddProductBinding
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref: DatabaseReference =database.reference.child("products")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AddProductActivity=ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(AddProductActivity.root)

        AddProductActivity.button.setOnClickListener {
            var Pname:String=AddProductActivity.sendPName.text.toString()
            var PPrice:String=AddProductActivity.sendPPrice.text.toString()
            var Pdesc:String=AddProductActivity.sendDesc.text.toString()
        }










        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}