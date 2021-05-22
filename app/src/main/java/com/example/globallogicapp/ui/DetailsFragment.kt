package com.example.globallogicapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.globallogicapp.databinding.FragmentDetailsBinding
import com.example.globallogicapp.domain.usecase.GetProductUseCase
import com.example.globallogicapp.ui.ProductFragment.Companion.ID_PRODUCT
import com.example.globallogicapp.viewmodel.DetailsViewModel
import org.koin.android.ext.android.inject

/**
 * @author Axel Sanchez
 */
class DetailsFragment : Fragment() {

    private val getProductUseCase: GetProductUseCase by inject()
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

            viewModel.getProductLiveData().observe(viewLifecycleOwner, { product ->
                with(binding){
                    product?.let {
                        title.text = it.title
                        description.text = it.description

                        Glide
                            .with(view)
                            .load(it.image)
                            .centerCrop()
                            .into(imageView)
                    }
                }
            })
        }
    }
}