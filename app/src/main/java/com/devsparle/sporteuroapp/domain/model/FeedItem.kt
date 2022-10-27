package com.devsparle.sporteuroapp.domain.model

sealed class FeedItem {
    class Story(
        val imageUrl: String?,
        val title: String?,
        val author: String?,
        val date: Float?
    ) : FeedItem()

    class Video(
        val videoUrl: String?,
        val title: String?,
        val date: Float?,
        val thumb: String?
    ) : FeedItem()
}