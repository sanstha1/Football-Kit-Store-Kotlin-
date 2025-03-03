package com.example.footballkitstore.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityAddProductBinding
import com.example.footballkitstore.model.ProductModel
import com.example.footballkitstore.repository.ProductRepositoryImplementation
import com.example.footballkitstore.utils.ImageUtils
import com.example.footballkitstore.utils.LoadingUtils
import com.example.footballkitstore.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso

class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding

    lateinit var productViewModel: ProductViewModel

    lateinit var loadingUtils: LoadingUtils

//    lateinit var  imageUtils: ImageUtils
//
//    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        imageUtils = ImageUtils(this)

        loadingUtils=LoadingUtils(this)

        var repo = ProductRepositoryImplementation()
        productViewModel = ProductViewModel(repo)

        binding.arrowbackadd.setOnClickListener{
            val intent = Intent(this@AddProductActivity, ProductDashboardActivity::class.java)
            startActivity(intent)
        }

//        imageUtils.registerActivity { url ->
//            url.let { it ->
//                imageUri = it
//                Picasso.get().load(it).into(binding.imageBrowse)
//            }
//        }
//        binding.imageBrowse.setOnClickListener {
//            imageUtils.launchGallery(this)
//        }
//        binding.addbutton.setOnClickListener {
//            uploadImage()
//
//        }


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

                    var intent = Intent(this@AddProductActivity,ProductDashboardActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(this@AddProductActivity,
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

//    private fun uploadImage() {
//        loadingUtils.show()
//        imageUri?.let { uri ->
//            productViewModel.uploadImage(this, uri) { imageUrl ->
//                Log.d("checpoirs", imageUrl.toString())
//                if (imageUrl != null) {
//                    addProduct(imageUrl)
//                } else {
//                    Log.e("Upload Error", "Failed to upload image to Cloudinary")
//                }
//            }
//        }
//    }

    private fun addProduct(url: String) {
        var productName = binding.productname.text.toString()
        var productPrice = binding.price.text.toString().toInt()
        var productDesc = binding.description.text.toString()

        var model = ProductModel(
            "",
            productName,
            productDesc, productPrice, url
        )

        productViewModel.addProduct(model) { success, message ->
            if (success) {
                Toast.makeText(
                    this@AddProductActivity,
                    message, Toast.LENGTH_LONG
                ).show()
                finish()
                loadingUtils.dismiss()
            } else {
                Toast.makeText(
                    this@AddProductActivity,
                    message, Toast.LENGTH_LONG
                ).show()
                loadingUtils.dismiss()
            }
        }
    }
}