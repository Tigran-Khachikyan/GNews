package com.gnews.ui

interface ViewModelContract<EVENT> {

    fun process(event: EVENT)
}
