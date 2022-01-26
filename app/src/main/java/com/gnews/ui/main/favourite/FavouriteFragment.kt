package com.gnews.ui.main.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gnews.databinding.FragmentFavouriteBinding
import com.gnews.ui.BaseMviFragment
import com.gnews.ui.main.favourite.Event.*
import com.gnews.ui.main.home.ArticleAdapter
import com.gnews.utils.visibleOrGone
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseMviFragment<State, Effect, Event, FavouriteViewModel>() {

    override val viewModel: FavouriteViewModel by viewModel()
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
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
                    onlyCached = true,
                    onViewContent = { title -> viewModel.process(ViewDetails(title)) },
                    onRemoveArticle = { title -> viewModel.process(Remove(title)) }
                )
                adapter = articleAdapter
            }
        }
    }

    override fun renderViewState(state: State) {
        with(binding) {
            articleAdapter.setListItems(state.savedArticled)
            tvEmptyList.visibleOrGone(state.savedArticled.isEmpty())
        }
    }

    override fun renderViewEffect(effect: Effect) {
        when (effect) {
            is Effect.NavigateTo.Details -> {
                findNavController().navigate(
                    FavouriteFragmentDirections.actionNavFavouriteToNavDetails(effect.article)
                )
            }
        }
    }
}
