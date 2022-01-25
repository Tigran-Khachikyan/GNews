package com.gnews.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gnews.databinding.FragmentHomeBinding
import com.gnews.ui.BaseMviFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseMviFragment<State, Effect, Event, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        with(binding) {}
    }

    override fun renderViewState(state: State) {
        with(binding) {}
    }

    override fun renderViewEffect(effect: Effect) {
    }
}
