package com.example.globallogicapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.domain.usecase.GetProductUseCase
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class DetailsViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val listData: MutableLiveData<Product?> = MutableLiveData<Product?>()

    private fun setListData(result: Product?) {
        listData.postValue(result)
    }

    fun getProduct(idProduct: Long) {
        viewModelScope.launch {
            setListData(getProductUseCase.call(idProduct))
        }
    }

    fun getProductLiveData(): LiveData<Product?> {
        return listData
    }

    class DetailsViewModelFactory(private val getProductUseCase: GetProductUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetProductUseCase::class.java)
                .newInstance(getProductUseCase)
        }
    }
}