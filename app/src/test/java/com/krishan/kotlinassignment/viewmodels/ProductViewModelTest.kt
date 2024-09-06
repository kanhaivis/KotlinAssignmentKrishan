package com.krishan.kotlinassignment.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kk.mymvvmapp.getOrAwaitValue
import com.krishan.kotlinassignment.api.NetworkRequest
import com.krishan.kotlinassignment.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductViewModelTest {

    val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var productRepository: ProductRepository

    lateinit var productViewModel: ProductViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productViewModel = ProductViewModel(productRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_get_product_success() = runTest {
        Mockito.`when`(productRepository.getProduct()).thenReturn(NetworkRequest.Success(emptyList()))

        val sut = ProductViewModel(productRepository)
        sut.getProduct()

        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.productList.getOrAwaitValue()

        assertEquals(0, result.data!!.size)
    }


    @Test
    fun test_get_product_error() = runTest {
        Mockito.`when`(productRepository.getProduct()).thenReturn(NetworkRequest.Error("Some error"))

        val sut = ProductViewModel(productRepository)
        sut.getProduct()

        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.productList.getOrAwaitValue()
        assertEquals(true, result is NetworkRequest.Error)
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}