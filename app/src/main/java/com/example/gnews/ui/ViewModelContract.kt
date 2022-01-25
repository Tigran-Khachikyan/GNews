package com.example.gnews.ui

interface ViewModelContract<EVENT> {

    fun process(event: EVENT)
}
