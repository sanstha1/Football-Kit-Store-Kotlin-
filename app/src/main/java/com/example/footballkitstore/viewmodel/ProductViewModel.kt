package com.example.footballkitstore.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.footballkitstore.model.ProductModel
import com.example.footballkitstore.repository.ProductRepository

class ProductViewModel (val repo: ProductRepository){
    fun addProduct(productModel: ProductModel,
                   callback:(Boolean,String)->Unit){
        repo.addProduct(productModel,callback)
    }

    fun updateProduct(productId : String,
                      data:MutableMap<String,Any>,
                      callback: (Boolean, String) -> Unit){
        repo.updateProduct(productId,data,callback)

    }

    fun deleteProduct(productId: String,
                      callback: (Boolean, String) -> Unit){
        repo.deleteProduct(productId,callback)
    }


    var _products = MutableLiveData<ProductModel>()
    var products = MutableLiveData<ProductModel>()
        get()=_products

    var _allproducts = MutableLiveData<List<ProductModel>>()
    var allproducts = MutableLiveData<List<ProductModel>>()
        get()=_allproducts


    fun getProductById(productId: String){
        repo.getProductById(productId){
            products,success,message->
            if(success){
                _products.value = products
            }
        }
    }

    var _loading = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
        get()= _loading

    fun getAllProduct(){
        _loading.value = true
        repo.getAllProduct{
                products,success,message->
            if(success){
                _allproducts.value = products
                _loading.value = false
            }
        }
    }
    fun uploadImage(context: Context, imageUri: Uri, callback: (String?) -> Unit){
        repo.uploadImage(context, imageUri, callback)
    }
}