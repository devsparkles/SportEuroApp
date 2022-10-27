package com.devsparle.sporteuroapp.domain.usecases

import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.domain.repository.remote.RemoteFeedRepository
import javax.inject.Inject

class GetFeed @Inject constructor(private val remoteFeedRepository: RemoteFeedRepository) {


    suspend operator fun invoke(): MutableList<FeedItem> {
        val feed = remoteFeedRepository.getFeed()
        if (feed.isNotAnError()) {
            val result = mutableListOf<FeedItem>()
            feed.value()?.stories?.let {
                result.addAll(it)
            }
            feed.value()?.videos?.let {
                result.addAll(it)
            }
            return result
        } else {
            return mutableListOf()
        }
    }
}