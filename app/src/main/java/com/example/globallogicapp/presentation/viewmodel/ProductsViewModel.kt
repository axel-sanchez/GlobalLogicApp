package com.example.globallogicapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.globallogicapp.data.model.ResultListProducts
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCase
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class ProductsViewModel(private val getAllProductsUseCase: GetAllProductsUseCase): ViewModel() {

    private val listData: MutableLiveData<ResultListProducts> = MutableLiveData<ResultListProducts>()

    private fun setListData(result: ResultListProducts) {
        listData.postValue(result)
    }

    fun getProducts() {
        viewModelScope.launch {
            setListData(getAllProductsUseCase.call())
        }
    }

    fun getProductLiveData(): LiveData<ResultListProducts> {
        return listData
    }

    class ProductViewModelFactory(private val getAllProductsUseCase: GetAllProductsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetAllProductsUseCase::class.java).newInstance(getAllProductsUseCase)
        }
    }
}