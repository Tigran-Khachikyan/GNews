package com.gnews.ui.main.details

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.gnews.R
import com.gnews.databinding.FragmentDetailsBinding
import com.gnews.ui.BaseMviFragment
import com.gnews.utils.px
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseMviFragment<State, Effect, Event, DetailsViewModel>() {

    override val viewModel: DetailsViewModel by viewModel()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initViews() {
        viewModel.process(Event.InitScreen(args.article))
    }

    override fun renderViewState(state: State) {
        with(binding) {
            state.article?.run {
                tvTitle.text = title

                val icPlaceholderRes = R.drawable.ic_news
                Picasso.get()
                    .load(image)
                    .centerCrop(Gravity.TOP)
                    .resize(200.px, 200.px)
                    .error(icPlaceholderRes)
                    .placeholder(icPlaceholderRes)
                    .into(ivPicture)

                tvContent.text = content
                tvSource.text = sourceName
                tvDate.text = publishedAt
            }
        }
    }

    override fun renderViewEffect(effect: Effect) {
    }
}
