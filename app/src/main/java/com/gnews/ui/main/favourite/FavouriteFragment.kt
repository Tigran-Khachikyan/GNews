package com.gnews.ui.main.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gnews.databinding.FragmentFavouriteBinding
import com.gnews.ui.BaseMviFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseMviFragment<State, Effect, Event, FavouriteViewModel>() {

    override val viewModel: FavouriteViewModel by viewModel()
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

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
        with(binding) {}
    }

    override fun renderViewState(state: State) {
        with(binding) {}
    }

    override fun renderViewEffect(effect: Effect) {
    }
}
