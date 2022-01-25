package com.gnews.ui.main.favourite

import com.gnews.data.network.Request
import com.gnews.ui.BaseEffect
import com.gnews.ui.BaseEvent
import com.gnews.ui.BaseState

class State : BaseState()

sealed class Effect : BaseEffect() {
    sealed class NavigateTo : Effect()
    data class ShowError(val reason: Request.Error) : Effect() {
        override val error: Request.Error
            get() = reason
    }
}

sealed class Event : BaseEvent()
