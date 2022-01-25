package com.example.gnews.data.network.mappers

interface Mapper<FROM, TO> {

    fun mapSecure(from: List<FROM?>?): List<TO> {
        val result: ArrayList<TO> = arrayListOf()
        if (!from.isNullOrEmpty()) {
            from.forEach { item ->
                if (item != null) {
                    transform(item)?.let {
                        result.add(it)
                    }
                }
            }
        }
        return result
    }

    fun transform(from: FROM): TO?
}
