package com.example.gnews.data.network.mappers

interface RequestMapper<MODEL, REQUEST> {

    fun requestFromModel(model: MODEL): REQUEST
}
