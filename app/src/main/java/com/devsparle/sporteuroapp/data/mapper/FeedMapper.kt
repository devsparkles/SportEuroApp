package com.devsparle.sporteuroapp.data.mapper

import com.devsparle.sporteuroapp.data.remote.feed.dto.FeedDto
import com.devsparle.sporteuroapp.data.remote.feed.dto.SportDto
import com.devsparle.sporteuroapp.data.remote.feed.dto.StoryDto
import com.devsparle.sporteuroapp.data.remote.feed.dto.VideoDto
import com.devsparle.sporteuroapp.domain.model.Feed
import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.domain.model.Sport
import com.devsparle.sporteuroapp.utils.ConvertUtils
import com.devsparle.sporteuroapp.utils.resource.Resource


fun Resource<FeedDto?>.toDomain(): Resource<Feed?> {
    return when (this) {
        is Resource.Success -> Resource.Success(
            this.value()?.toDomain()
        )
        is Resource.SuccessWithoutContent -> Resource.SuccessWithoutContent()
        is Resource.Error -> Resource.Error(this.error())
        is Resource.Loading -> Resource.Loading()
    }
}

fun VideoDto.toDomain(): FeedItem.Video {
    return FeedItem.Video(
        videoUrl = this.url,
        title = this.title,
        date = this.date,
        thumb = this.thumb,
        sport = this.sport.toDomain(),
        views = this.views,
        humanTimeAgo = ConvertUtils.getPrettyTime(this.date)
    )
}

fun StoryDto.toDomain(): FeedItem.Story {
    return FeedItem.Story(
        imageUrl = this.image,
        title = this.title,
        author = this.author,
        date = this.date,
        teaser =  this.teaser,
        sport = this.sport.toDomain(),
        humanTimeAgo = ConvertUtils.getPrettyTime(this.date)
    )
}

fun SportDto.toDomain(): Sport {
    return Sport(
        id = this.id,
        name = this.name
    )
}

fun List<VideoDto>?.toVideoDomain(): MutableList<FeedItem.Video> {
    val result: MutableList<FeedItem.Video> = mutableListOf()
    this?.forEach { result.add(it.toDomain()) }
    return result
}


fun List<StoryDto>?.toStoryDomain(): MutableList<FeedItem.Story> {
    val result: MutableList<FeedItem.Story> = mutableListOf()
    this?.forEach { result.add(it.toDomain()) }
    return result
}

fun FeedDto?.toDomain(): Feed {
    return Feed(
        videos = this?.videos.toVideoDomain(),
        stories = this?.stories.toStoryDomain()
    )
}

