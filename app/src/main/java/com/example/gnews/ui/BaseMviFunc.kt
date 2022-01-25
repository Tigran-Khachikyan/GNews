package com.example.gnews.ui

import com.example.gnews.data.network.Request

abstract class BaseState {
    var status: ScreenStatus = ScreenStatus.INITIAL
}

abstract class BaseEffect {
    open val error: Request.Error? = null
}

abstract class BaseEvent

enum class ScreenStatus {
    INITIAL,
    LOADING,
    FETCHED
}
