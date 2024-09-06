package com.krishan.kotlinassignment

import com.krishan.kotlinassignment.api.NetworkAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var networkAPI: NetworkAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        networkAPI = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(networkAPI::class.java)
    }

    @Test
    fun testGetProducts() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = networkAPI.getProductList()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}