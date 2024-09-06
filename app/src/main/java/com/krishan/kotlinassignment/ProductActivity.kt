package com.krishan.kotlinassignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ProgressBar
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.krishan.kotlinassignment.adapter.ProductAdapter
import com.krishan.kotlinassignment.callbackmethod.AlertCallBack
import com.krishan.kotlinassignment.modules.ProductModuleItem
import com.krishan.kotlinassignment.utils.AppAlertDialog.onShowAlert
import com.krishan.kotlinassignment.utils.CommonMethod
import com.krishan.kotlinassignment.viewmodels.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar


    var startForResult: ActivityResultLauncher<Intent> =
        registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->

            if (result.resultCode == Activity.RESULT_OK) {
                productViewModel.getProduct()
            } else {
                if (!CommonMethod.checkForInternet(this)) {
                    onIntentGo()
                } else {
                    productViewModel.getProduct()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerview)
        progressBar = findViewById(R.id.progressBar)

        adapter = ProductAdapter(listOf())
        mRecyclerView.adapter = adapter

        if (CommonMethod.checkForInternet(this)) {
            productViewModel.getProduct()
        } else {
            onIntentGo()
        }

        productViewModel.productList.observe(this) {
            onProductDataUI(it.data!!)
            progressBar.visibility = View.GONE
        }
    }

   private fun onIntentGo() {
        onShowAlert(this, "No Internet", object : AlertCallBack {
            override fun onCancel() {
                finish()
            }
            override fun onRetry() {
                startForResult.launch(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        })
    }

    private fun onProductDataUI(data: List<ProductModuleItem>?) {
        adapter.updateProductList(data!!)
    }
}