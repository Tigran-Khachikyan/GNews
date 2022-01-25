package com.gnews.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gnews.data.network.Request
import com.gnews.utils.visibleOrGone
import timber.log.Timber

abstract class BaseMviFragment<STATE : BaseState, EFFECT : BaseEffect, EVENT : BaseEvent, ViewModel : BaseMviViewModel<STATE, EFFECT, EVENT>> :
    Fragment() {

    abstract val viewModel: ViewModel

    private val viewStateObserver = Observer<STATE?> { state ->
        state?.let {
            (requireActivity() as BaseActivity).progressBar.visibleOrGone(it.status == ScreenStatus.LOADING)
            Timber.d("observed viewState : $it")
            renderViewState(it)
        }
    }

    private val viewEffectObserver = Observer<EFFECT?> { effect ->
        effect?.let {
            /** In case of slow connection (Timeout) or Server side issues**/
            if (it.error == Request.Error.ConnectionIssue) {
                // TODO
            }
            Timber.d("observed viewEffect : $it")
            renderViewEffect(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        with(viewModel) {
            viewStates().observe(viewLifecycleOwner, viewStateObserver)
            viewEffects().observe(viewLifecycleOwner, viewEffectObserver)
        }
    }

    abstract fun initViews()

    abstract fun renderViewState(state: STATE)

    abstract fun renderViewEffect(effect: EFFECT)
}
