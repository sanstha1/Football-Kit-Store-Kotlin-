package com.example.footballkitstore.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityUpdateProductBinding
import com.example.footballkitstore.repository.ProductRepositoryImplementation
import com.example.footballkitstore.viewmodel.ProductViewModel

class UpdateProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateProductBinding

    lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = ProductRepositoryImplementation()
        productViewModel = ProductViewModel(repo)

        var productId : String = intent.getStringExtra("productId").toString()

        productViewModel.getProductById(productId)

        productViewModel.products.observe(this){
            binding.updateproduct.setText(it?.productName.toString())
            binding.updatedesc.setText(it?.productDesc.toString())
            binding.updateprice.setText(it?.price.toString())
        }

        binding.updatebtn.setOnClickListener{
            var name = binding.updateproduct.text.toString()
            var desc = binding.updatedesc.text.toString()
            var price = binding.updateprice.text.toString().toInt()

            var updatedMap = mutableMapOf<String,Any>()

            updatedMap["productName"] = name
            updatedMap["productDesc"] = desc
            updatedMap["price"] = price

            productViewModel.updateProduct(productId,updatedMap){
                success,message->
                if(success){
                    Toast.makeText(this@UpdateProductActivity,
                        message, Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@UpdateProductActivity,
                        message,Toast.LENGTH_SHORT).show()
                }
            }


        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}