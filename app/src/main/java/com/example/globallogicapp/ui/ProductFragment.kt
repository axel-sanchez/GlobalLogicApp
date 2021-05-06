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
import androidx.recyclerview.widget.RecyclerView
import com.example.globallogicapp.R
import com.example.globallogicapp.data.Result
import com.example.globallogicapp.data.Result.*
import com.example.globallogicapp.databinding.FragmentProductBinding
import com.example.globallogicapp.domain.usecase.GetProductsUseCase
import com.example.globallogicapp.helpers.Either
import com.example.globallogicapp.helpers.hide
import com.example.globallogicapp.helpers.show
import com.example.globallogicapp.ui.adapters.ProductAdapter
import com.example.globallogicapp.viewmodel.ProductViewModel
import org.koin.android.ext.android.inject

/**
 * A fragment representing a list of Items.
 */
class ProductFragment : Fragment() {

    private val getProductsUseCase: GetProductsUseCase by inject()
    private val viewModel: ProductViewModel by viewModels(
        factoryProducer = { ProductViewModel.ProductViewModelFactory(getProductsUseCase) }
    )

    private var fragmentProductBinding: FragmentProductBinding? = null
    private val binding get() = fragmentProductBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            binding.progress.hide()
            if (response is Either.Right) {
                // Set the adapter
                with(binding.list) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ProductAdapter(response.r) { itemClick(it) }
                }
            }
        })
    }

    private fun itemClick(resultItem: ResultItem) {
        val bundle = bundleOf(
            RESULT_ITEM to resultItem
        )
        findNavController().navigate(R.id.action_productFragment_to_blankFragment, bundle)
    }

    companion object {
        const val RESULT_ITEM = "resultItem"
    }
}