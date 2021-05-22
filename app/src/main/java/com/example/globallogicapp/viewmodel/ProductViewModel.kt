package com.example.globallogicapp.viewmodel

import androidx.lifecycle.*
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCase
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class ProductViewModel(private val getAllProductsUseCase: GetAllProductsUseCase): ViewModel() {

    private val listData: MutableLiveData<Either<Constants.ApiError, List<Product?>>> by lazy {
        MutableLiveData<Either<Constants.ApiError, List<Product?>>>().also {
            getProduct()
        }
    }

    private fun setListData(result: Either<Constants.ApiError, List<Product?>>) {
        listData.postValue(result)
    }

    private fun getProduct() {
        viewModelScope.launch {
            setListData(getAllProductsUseCase.call())
        }
    }

    fun getProductLiveData(): LiveData<Either<Constants.ApiError, List<Product?>>> {
        return listData
    }

    class ProductViewModelFactory(private val getAllProductsUseCase: GetAllProductsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetAllProductsUseCase::class.java).newInstance(getAllProductsUseCase)
        }
    }
}