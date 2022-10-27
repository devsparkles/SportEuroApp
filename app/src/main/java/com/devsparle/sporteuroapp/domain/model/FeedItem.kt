package com.devsparle.sporteuroapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class FeedItem {
    @Parcelize
    class Story(
        val imageUrl: String = "",
        val title: String = "",
        val author: String = "",
        val sport: Sport = Sport(),
        val date: Double = 0.0,
        val humanTimeAgo: String = ""
    ) : FeedItem(), Parcelable

    @Parcelize
    class Video(
        val videoUrl: String = "",
        val title: String = "",
        val date: Double = 0.0,
        val sport: Sport = Sport(),
        val thumb: String = "",
        val views: Long = 0L,
        val humanTimeAgo: String = ""
    ) : FeedItem(), Parcelable
}
