package com.example.globallogicapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globallogicapp.R
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.databinding.FragmentProductBinding
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCase
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either
import com.example.globallogicapp.helpers.hide
import com.example.globallogicapp.helpers.show
import com.example.globallogicapp.ui.adapters.ProductAdapter
import com.example.globallogicapp.viewmodel.ProductsViewModel
import org.koin.android.ext.android.inject

/**
 * A fragment representing a list of Items.
 */
class ProductsFragment : Fragment() {

    private val getAllProductsUseCase: GetAllProductsUseCase by inject()
    private val viewModel: ProductsViewModel by viewModels(
        factoryProducer = { ProductsViewModel.ProductViewModelFactory(getAllProductsUseCase) }
    )

    private var fragmentProductBinding: FragmentProductBinding? = null
    private val binding get() = fragmentProductBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentProductBinding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentProductBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductLiveData().observe(viewLifecycleOwner, { response ->
            updateView(response)
        })
    }

    private fun updateView(response: Either<Constants.ApiError, List<Product?>>?) {
        with(binding) {
            response?.fold(
                left = {
                    emptyState.show()
                    errorText.text = getString(R.string.error_api_products)
                    list.hide()
                }, right = {
                    if ((response as Either.Right).r.isEmpty()) {
                        list.hide()
                        errorText.text = getString(R.string.there_is_not_products)
                        emptyState.show()
                    } else {
                        list.show()
                        setAdapter(response.r)
                    }
                }
            )
            progress.hide()
        }
    }

    private fun setAdapter(products: List<Product?>) {
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(products) { itemClick(it) }
        }
    }

    private fun itemClick(product: Product?) {
        val bundle = bundleOf(
            ID_PRODUCT to product?.id
        )
        findNavController().navigate(R.id.action_productFragment_to_blankFragment, bundle)
    }

    companion object {
        const val ID_PRODUCT = "idProduct"
    }
}