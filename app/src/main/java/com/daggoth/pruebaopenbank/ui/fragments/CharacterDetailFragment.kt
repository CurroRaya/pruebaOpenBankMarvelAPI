package com.daggoth.pruebaopenbank.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.daggoth.pruebaopenbank.data.model.Results
import com.daggoth.pruebaopenbank.databinding.FragmentDetailBinding
import com.daggoth.pruebaopenbank.ui.viewmodels.DetailViewModel
import com.daggoth.pruebaopenbank.ui.viewmodels.factory.DetailViewModelFactory
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var character: Results? = null
    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments?.getSerializable("characterData") as Results?

        val _detailViewModel: DetailViewModel by viewModels { DetailViewModelFactory(character) }
        detailViewModel = _detailViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
    }

    private fun setupObserver(){
        detailViewModel.characterItem.observe(viewLifecycleOwner,  {
            bindElements(it)
        })
    }

    private fun bindElements(detail: Results?) {
        with(binding) {
            detailName.text = detail?.name
            detailDescription.text = if(detail?.description.isNullOrEmpty()) "NO DESCRIPTION" else detail?.description
            Picasso.with(binding.root.context)
                .load(detail?.getImageUrl())
                .into(detailImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}