package com.example.footballkitstore.repository

import com.example.footballkitstore.model.ProductModel

interface ProductRepository {

    //{
    // "Success" : true,
      //  "Message" in addproduct and update product : "Product Added Successfully"
    //Message in delete : "Product Deleted Successfully""
    // }

    fun addProduct(productModel: ProductModel,
                   callback:(Boolean,String)->Unit)

    fun updateProduct(productId : String,
                      data:MutableMap<String,Any>,
                      callback: (Boolean, String) -> Unit)   //String =   //Any = DataType

    fun deleteProduct(productId: String,
                      callback: (Boolean, String) -> Unit)

    fun getProductById(productId: String,
                       callback: (ProductModel?,Boolean, String) -> Unit) //? = nullable which means that data may be there or not = data huna ni skxa nahuna ni sakxa

    fun getAllProduct(callback: (List<ProductModel>?,
                                 Boolean, String) -> Unit)

}