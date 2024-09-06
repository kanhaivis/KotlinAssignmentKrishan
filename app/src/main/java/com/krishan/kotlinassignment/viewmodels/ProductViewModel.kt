package com.krishan.kotlinassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishan.kotlinassignment.api.NetworkRequest
import com.krishan.kotlinassignment.modules.ProductModuleItem
import com.krishan.kotlinassignment.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<NetworkRequest<List<ProductModuleItem>>>()
    val productList : LiveData<NetworkRequest<List<ProductModuleItem>>>
        get() = mutableLiveData

    fun getProduct(){
        viewModelScope.launch {
            val result = productRepository.getProduct()
            mutableLiveData.value = result
        }
    }

}