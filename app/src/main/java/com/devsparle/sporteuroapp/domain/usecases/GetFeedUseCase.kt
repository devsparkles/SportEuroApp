package com.devsparle.sporteuroapp.domain.usecases

import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.domain.repository.remote.RemoteFeedRepository
import com.devsparle.sporteuroapp.utils.safeLet
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(private val remoteFeedRepository: RemoteFeedRepository) {


    suspend operator fun invoke(): MutableList<FeedItem> {
        val feed = remoteFeedRepository.getFeed()
        if (feed.isNotAnError()) {


            val s = feed.value()?.stories?.sortedByDescending { it.date }
            val v = feed.value()?.videos?.sortedByDescending { it.date }
            val re = safeLet(s, v) { s, v ->
                sequence {
                    val stories = s.iterator()
                    val videos = v.iterator()
                    while (stories.hasNext() && videos.hasNext()) {
                        yield(stories.next())
                        yield(videos.next())
                    }
                    yieldAll(stories)
                    yieldAll(videos)
                }.toMutableList()
            }

            re?.let {
                return it.toMutableList()
            }
            return mutableListOf()
        } else {
            return mutableListOf()
        }
    }
}