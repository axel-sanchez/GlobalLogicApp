package com.example.globallogicapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.globallogicapp.data.Result
import com.example.globallogicapp.data.Result.*
import com.example.globallogicapp.databinding.FragmentDetailsBinding
import com.example.globallogicapp.ui.ProductFragment.Companion.RESULT_ITEM

/**
 * @author Axel Sanchez
 */
class DetailsFragment : Fragment() {

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

        val resultItem = arguments?.getParcelable<ResultItem>(RESULT_ITEM)

        binding.title.text = resultItem?.title
        binding.description.text = resultItem?.description

        Glide
            .with(view)
            .load(resultItem?.image)
            .centerCrop()
            .into(binding.imageView)
    }
}