package com.krishan.kotlinassignment.repository

import com.krishan.kotlinassignment.api.NetworkAPI
import com.krishan.kotlinassignment.api.NetworkRequest
import com.krishan.kotlinassignment.modules.ProductModuleItem
import javax.inject.Inject

class ProductRepository @Inject constructor(private val networkAPI: NetworkAPI) {

    suspend fun getProduct(): NetworkRequest<List<ProductModuleItem>> {
        val response = networkAPI.getProductList()

        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkRequest.Success(responseBody)
            } else {
                NetworkRequest.Error("API error..")
            }
        } else {
            NetworkRequest.Error("Exception error...")
        }
    }


}