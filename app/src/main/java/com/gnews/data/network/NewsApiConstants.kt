package com.gnews.data.network

enum class Topics(val title: String) {
    BREAKING_NEWS("breaking-news"),
    WORLD("world"),
    NATION("nation"),
    BUSINESS("business"),
    TECHNOLOGY("technology"),
    ENTERTAINMENT("entertainment"),
    SPORTS("sports"),
    SCIENCE("science"),
    HEALTH("health")
}

enum class Language(val title: String) {
    GERMAN("de"),
    SPANISH("es"),
    RUSSIAN("ru"),
    ENGLISH("en")
}
