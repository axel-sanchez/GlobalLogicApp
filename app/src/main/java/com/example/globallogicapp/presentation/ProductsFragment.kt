package com.example.globallogicapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.globallogicapp.R
import com.example.globallogicapp.core.MyApplication
import com.example.globallogicapp.databinding.FragmentProductBinding
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCase
import com.example.globallogicapp.presentation.viewmodel.ProductsViewModel
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class ProductsFragment : Fragment() {

    @Inject lateinit var getAllProductsUseCase: GetAllProductsUseCase

    lateinit var binding : FragmentProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: ProductsViewModel by viewModels(
        factoryProducer = { ProductsViewModel.ProductViewModelFactory(getAllProductsUseCase) }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getProducts()

        return binding.root
    }

    companion object {
        const val ID_PRODUCT = "idProduct"
    }
}