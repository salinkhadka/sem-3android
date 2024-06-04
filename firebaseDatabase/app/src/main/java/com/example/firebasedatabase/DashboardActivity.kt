package com.example.firebasedatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasedatabase.Adapter.ProductAdapter
import com.example.firebasedatabase.databinding.ActivityDashboardBinding
import com.example.firebasedatabase.model.ProductModel
//import com.example.firebasedatabase.adapter.ProductAdapter
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref: DatabaseReference = database.reference.child("products")

    private var productList = ArrayList<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (eachData in snapshot.children) {
                    val product = eachData.getValue(ProductModel::class.java)
                    if (product != null) {
                        Log.d("database bata", product.id)
                        Log.d("database bata", product.name)
                        Log.d("database bata", product.price.toString())
                        Log.d("database bata", product.description)

                        productList.add(product)
                    }
                }
                val adapter = ProductAdapter(this@DashboardActivity,productList)
                binding.recyclerView.layoutManager = LinearLayoutManager(this@DashboardActivity)
                binding.recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DashboardActivity", "Database error: ${error.message}")
            }
        })

        binding.fab.setOnClickListener {
            val intent = Intent(this@DashboardActivity, AddProductActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
