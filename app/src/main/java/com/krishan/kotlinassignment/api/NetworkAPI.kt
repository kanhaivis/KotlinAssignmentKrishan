package com.krishan.kotlinassignment.api

import com.krishan.kotlinassignment.modules.ProductModuleItem
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPI {
    @GET("b/5BEJ")
    suspend fun getProductList() : Response<List<ProductModuleItem>>
}