package com.devsparle.sporteuroapp.data.remote.feed.dto

data class VideoDto(
    val id: Long,
    val title: String,
    val thumb: String,
    val url: String,
    val date: Double,
    val sport: SportDto,
    val views: Long
)


