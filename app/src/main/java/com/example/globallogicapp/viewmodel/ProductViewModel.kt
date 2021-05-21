package com.example.globallogicapp.viewmodel

import androidx.lifecycle.*
import com.example.globallogicapp.data.model.Result
import com.example.globallogicapp.domain.usecase.GetProductsUseCase
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class ProductViewModel(private val getProductsUseCase: GetProductsUseCase): ViewModel() {

    private val listData: MutableLiveData<Either<Constants.ApiError, Result>> by lazy {
        MutableLiveData<Either<Constants.ApiError, Result>>().also {
            getProduct()
        }
    }

    private fun setListData(result: Either<Constants.ApiError, Result>) {
        listData.postValue(result)
    }

    private fun getProduct() {
        viewModelScope.launch {
            setListData(getProductsUseCase.call())
        }
    }

    fun getProductLiveData(): LiveData<Either<Constants.ApiError, Result>> {
        return listData
    }

    class ProductViewModelFactory(private val getProductsUseCase: GetProductsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetProductsUseCase::class.java).newInstance(getProductsUseCase)
        }
    }
}