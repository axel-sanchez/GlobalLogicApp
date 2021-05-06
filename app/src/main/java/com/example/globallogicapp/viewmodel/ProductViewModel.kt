package com.example.globallogicapp.viewmodel

import androidx.lifecycle.*
import com.example.globallogicapp.data.Result
import com.example.globallogicapp.data.source.ApiError
import com.example.globallogicapp.domain.usecase.GetProductsUseCase
import com.example.globallogicapp.domain.usecase.GetProductsUseCaseImpl
import com.example.globallogicapp.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class ProductViewModel(private val getProductsUseCase: GetProductsUseCase): ViewModel() {

    private val listData: MutableLiveData<Either<ApiError, Result>> by lazy {
        MutableLiveData<Either<ApiError, Result>>().also {
            getProduct()
        }
    }

    private fun setListData(result: Either<ApiError, Result>) {
        listData.postValue(result)
    }

    private fun getProduct() {
        viewModelScope.launch {
            setListData(getProductsUseCase.call())
        }
    }

    fun getProductLiveData(): LiveData<Either<ApiError, Result>> {
        return listData
    }

    class ProductViewModelFactory(private val getProductsUseCase: GetProductsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetProductsUseCase::class.java).newInstance(getProductsUseCase)
        }
    }
}