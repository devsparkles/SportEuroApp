package com.devsparle.sporteuroapp.data.remote.feed.dto

data class StoryDto(
    val id: Long,
    val title: String,
    val teaser: String,
    val image: String,
    val date: Float,
    val author: String,
    val sport: SportDto,
)