package com.example.gnews.data.network.mappers

interface ResponseMapper<RESPONSE, MODEL> {

    fun modelFromResponse(response: RESPONSE): MODEL
}
