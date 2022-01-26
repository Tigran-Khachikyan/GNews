package com.gnews.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gnews.databinding.FragmentHomeBinding
import com.gnews.ui.BaseMviFragment
import com.gnews.ui.main.home.Event.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseMviFragment<State, Effect, Event, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initViews() {
        with(binding) {
            recyclerArticles.apply {
                setHasFixedSize(true)
                articleAdapter = ArticleAdapter(
                    onViewContent = { title -> viewModel.process(ViewDetails(title)) },
                    onFavouriteMarkChanged = { title, selected ->
                        viewModel.process(MarkAsFavourite(title, selected))
                    }
                )
                adapter = articleAdapter
            }
        }
    }

    override fun renderViewState(state: State) {
        with(binding) {
            articleAdapter.setListItems(state.articles)
        }
    }

    override fun renderViewEffect(effect: Effect) {
    }
}
