package com.example.globallogicapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.globallogicapp.core.MyApplication
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.databinding.FragmentDetailsBinding
import com.example.globallogicapp.domain.usecase.GetProductUseCase
import com.example.globallogicapp.helpers.load
import com.example.globallogicapp.presentation.ProductsFragment.Companion.ID_PRODUCT
import com.example.globallogicapp.presentation.viewmodel.DetailsViewModel
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
class DetailsFragment : Fragment() {

    @Inject lateinit var getProductUseCase: GetProductUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = { DetailsViewModel.DetailsViewModelFactory(getProductUseCase) }
    )

    private var fragmentDetailsBinding: FragmentDetailsBinding? = null
    private val binding get() = fragmentDetailsBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailsBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idProduct = arguments?.getLong(ID_PRODUCT)

        idProduct?.let {
            viewModel.getProduct(idProduct)

            viewModel.getProductLiveData().observe(viewLifecycleOwner) { product ->
                updateView(product)
            }
        }
    }

    fun updateView(product: Product?) {
        with(binding){
            product?.let {
                title.text = it.title
                description.text = it.description
                imageView.load(it.image)
            }
        }
    }
}