package com.example.footballkitstore.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityAddProductBinding
import com.example.footballkitstore.model.ProductModel
import com.example.footballkitstore.repository.ProductRepositoryImplementation
import com.example.footballkitstore.utils.LoadingUtils
import com.example.footballkitstore.viewmodel.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    lateinit var productViewModel: ProductViewModel
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils=LoadingUtils(this)

        var repo = ProductRepositoryImplementation()
        productViewModel = ProductViewModel(repo)

        binding.addbutton.setOnClickListener{
            val name = binding.productname.text.toString()
            val description = binding.description.text.toString()
            val price = binding.price.text.toString().toInt()

            var model = ProductModel("",name,description,price)

            productViewModel.addProduct(model){
                success,message->
                if(success){
                    Toast.makeText(this@AddProductActivity,
                        message,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@AddProductActivity,
                        message,Toast.LENGTH_SHORT).show()
                }
            }
        }

        setContentView(R.layout.activity_add_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}