package com.example.firebasedatabase.ViewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasedatabase.Ui.Activity.UpdateProduct
import com.example.firebasedatabase.model.ProductModel
import com.example.firebasedatabase.repository.ProductRepository

class ProductViewModel(val repository: ProductRepository) : ViewModel() {
    fun addProduct(productModel: ProductModel, callback: (Boolean, String?) -> Unit) {
        repository.addProduct(productModel, callback)
    }

    fun uploadImage(imageName:String,imageURL: Uri, callback: (Boolean, String?) -> Unit) {
        repository.uploadImage(imageName,imageURL) { sucess, imageUrl ->
            callback(sucess, imageUrl)
        }
    }

    var _productList = MutableLiveData<List<ProductModel>?>()
    var productList = MutableLiveData<List<ProductModel>?>()
        get() = _productList


    var _loadingState = MutableLiveData<Boolean>()
    var loadingState = MutableLiveData<Boolean>()
        get() = _loadingState



    fun fetchProduct() {
        _loadingState.value = true
        repository.getProduct{ productList, sucess, message ->
            if (productList != null) {

                _loadingState.value = false
                _productList.value = productList
            }
        }
    }

    fun UpdateProduct(id:String,data: MutableMap<String,Any>?,callback: (Boolean, String?) -> Unit){
        repository.updateProduct(id, data, callback)
    }




}