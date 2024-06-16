package com.example.firebasedatabase.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedatabase.Adapter.ProductAdapter
import com.example.firebasedatabase.R
import com.example.firebasedatabase.ViewModel.ProductViewModel
import com.example.firebasedatabase.databinding.ActivityDashboardBinding
import com.example.firebasedatabase.model.ProductModel
import com.example.firebasedatabase.repository.ProductRepositoryImpl
//import com.example.firebasedatabase.adapter.ProductAdapter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding
    lateinit var productViewModel: ProductViewModel
    lateinit var productAdapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = ProductRepositoryImpl()
        productViewModel = ProductViewModel(repo)
        productViewModel.fetchProduct()

        productAdapter = ProductAdapter(this@DashboardActivity, ArrayList())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = productAdapter
        }
        productViewModel.loadingState.observe(this) { loading ->
            if (loading) {
                binding.progressBar3.visibility = View.VISIBLE
            } else {
                binding.progressBar3.visibility = View.GONE
            }

        }
        productViewModel.productList.observe(this) { products ->
            products?.let {
                productAdapter.updateData(it)
            }
        }


        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var id =
                    productAdapter.getProductID(viewHolder.adapterPosition)
                var imageName =
                    productAdapter.getImageName(viewHolder.adapterPosition)
                productViewModel.deleteData(id){
                    sucess,message->
                    if (sucess){
                        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                        productViewModel.deleteImage(imageName){
                            sucess,message->

                        }
                    }
                    else{
                        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }).attachToRecyclerView(binding.recyclerView)


//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                productList.clear()
//                for (eachData in snapshot.children) {
//                    val product = eachData.getValue(ProductModel::class.java)
//                    if (product != null) {
//                        Log.d("database bata", product.id)
//                        Log.d("database bata", product.name)
//                        Log.d("database bata", product.price.toString())
//                        Log.d("database bata", product.description)
//
//                        productList.add(product)
//                    }
//                }
//
//                binding.recyclerView.layoutManager = LinearLayoutManager(this@DashboardActivity)
//                binding.recyclerView.adapter = productAdapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("DashboardActivity", "Database error: ${error.message}")
//            }
//        })

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
