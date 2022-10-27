package com.devsparle.sporteuroapp.data.remote.feed.dto

data class StoryDto(
    val id: Long = 0L,
    val title: String = "",
    val teaser: String = "",
    val image: String = "",
    val date: Double = 0.0,
    val author: String = "",
    val sport: SportDto = SportDto(),
)