package com.devsparle.sporteuroapp.data.remote.feed.dto

data class VideoDto(
    val id: Long,
    val title: String,
    val thumb: String,
    val url: String,
    val date: Float,
    val sport: SportDto,
    val views: Long
)


