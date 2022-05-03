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
import com.example.globallogicapp.databinding.FragmentDetailsBinding
import com.example.globallogicapp.domain.usecase.GetProductUseCase
import com.example.globallogicapp.presentation.ProductsFragment.Companion.ID_PRODUCT
import com.example.globallogicapp.presentation.viewmodel.DetailsViewModel
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
class DetailsFragment : Fragment() {

    @Inject lateinit var getProductUseCase: GetProductUseCase

    lateinit var binding : FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = { DetailsViewModel.DetailsViewModelFactory(getProductUseCase) }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val idProduct = arguments?.getLong(ID_PRODUCT)

        idProduct?.let {
            viewModel.getProduct(idProduct)
        }

        return binding.root
    }
}