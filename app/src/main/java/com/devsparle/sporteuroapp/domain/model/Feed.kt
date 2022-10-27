package com.devsparle.sporteuroapp.domain.model

data class Feed(
    val videos: List<FeedItem.Video>?,
    val stories: List<FeedItem.Story>?
)
