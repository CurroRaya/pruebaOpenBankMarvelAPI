package com.daggoth.pruebaopenbank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.daggoth.pruebaopenbank.R
import com.daggoth.pruebaopenbank.data.api.RestApiImpl
import com.daggoth.pruebaopenbank.data.api.RetrofitBuilder
import com.daggoth.pruebaopenbank.data.model.Results
import com.daggoth.pruebaopenbank.databinding.FragmentCharacterslistBinding
import com.daggoth.pruebaopenbank.intent.MainIntent
import com.daggoth.pruebaopenbank.intent.state.MainState
import com.daggoth.pruebaopenbank.ui.adapters.CharacterAdapter
import com.daggoth.pruebaopenbank.ui.adapters.listeners.OnCharacterClickListener
import com.daggoth.pruebaopenbank.ui.viewmodels.ListViewModel
import com.daggoth.pruebaopenbank.ui.viewmodels.factory.ListViewModelFactory
import kotlinx.android.synthetic.main.fragment_characterslist.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersListFragment : Fragment() {

    private var _binding: FragmentCharacterslistBinding? = null

    @ExperimentalCoroutinesApi
    private val listViewModel: ListViewModel by viewModels { ListViewModelFactory(
        RestApiImpl(RetrofitBuilder.apiService)) }

    private val mainAdapter by lazy {
        CharacterAdapter(requireContext(), ArrayList(), object: OnCharacterClickListener{
            override fun onCharacterClick(item: Results) {
                lifecycleScope.launch {
                    val bundle = bundleOf(
                        "characterData" to item
                    )
                    findNavController(this@CharactersListFragment).navigate(
                        R.id.action_FirstFragment_to_SecondFragment,
                        bundle
                    )
                }
            }
        })
    }

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterslistBinding.inflate(inflater, container, false)
        return binding.root

    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupClicks()
        observeViewModel()
        if(mainAdapter.itemCount == 0)
            fetchGetCharacters()
    }

    @ExperimentalCoroutinesApi
    private fun fetchGetCharacters() {
        lifecycleScope.launch {
            listViewModel.userIntent.send(MainIntent.FetchCharacters)
        }
    }

    @ExperimentalCoroutinesApi
    private fun getMoreCharacters() {
        lifecycleScope.launch {
            listViewModel.userIntent.send(MainIntent.GetMoreCharacters)
        }
    }

    @ExperimentalCoroutinesApi
    private fun setupUI(){
        character_list_recycler_view.run {
            adapter = mainAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)){
                        getMoreCharacters()
                    }
                }

            })
        }
    }

    @ExperimentalCoroutinesApi
    private fun setupClicks(){
        characters_retry_button.setOnClickListener {
            fetchGetCharacters()
        }
    }

    @ExperimentalCoroutinesApi
    private fun observeViewModel(){
        lifecycleScope.launch {
            listViewModel.mainState.collect { mainState ->
                when(mainState){
                    is MainState.Loading -> {
                        character_list_recycler_view.visibility = View.VISIBLE
                        characters_progress_bar.visibility = View.VISIBLE
                        characters_retry_button.visibility = View.GONE
                    }

                    is MainState.LoadTasks -> {
                        renderList(mainState.characters.data.results)
                    }

                    is MainState.Error -> {
                        character_list_recycler_view.visibility = View.GONE
                        characters_progress_bar.visibility = View.GONE
                        characters_retry_button.visibility = View.VISIBLE
                        Toast.makeText(context, "Error: ${mainState.error}", Toast.LENGTH_SHORT).show()
                    }
                    is MainState.Idle -> {
                        characters_retry_button.visibility = View.GONE
                        characters_progress_bar.visibility = View.GONE
                        character_list_recycler_view.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private fun renderList(listTodoTask: ArrayList<Results>){
        mainAdapter.setChatacterList(listTodoTask)
        listViewModel.setIdleFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}