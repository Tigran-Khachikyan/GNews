package com.gnews.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.gnews.databinding.FragmentSearchBinding
import com.gnews.ui.BaseMviFragment
import com.gnews.ui.main.details.SearchableAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseMviFragment<State, Effect, Event, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchableAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initViews() {
        with(binding) {
            recycler.apply {
                setHasFixedSize(true)
                searchAdapter = SearchableAdapter { title -> }
                adapter = searchAdapter
            }
            search.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        viewModel.process(Event.Search(p0 ?: ""))
                        return true
                    }
                })
            }
        }
    }

    override fun renderViewState(state: State) {
        with(binding) {
            searchAdapter.setListItems(state.articles)
        }
    }

    override fun renderViewEffect(effect: Effect) {
    }
}
