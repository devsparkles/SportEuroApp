package com.devsparle.sporteuroapp.data.remote.feed.repository

import com.devsparle.sporteuroapp.data.mapper.toDomain
import com.devsparle.sporteuroapp.data.remote.feed.service.FeedService
import com.devsparle.sporteuroapp.domain.model.Feed
import com.devsparle.sporteuroapp.domain.repository.remote.RemoteFeedRepository
import com.devsparle.sporteuroapp.utils.resource.Resource

class RemoteFeedRepositoryImpl(private val feedService: FeedService) : RemoteFeedRepository {

    override suspend fun getFeed(): Resource<Feed?> {
        try {
            val response = Resource.of { feedService.getFeedInfo() }
            // if we wanted to call other services to combine their information we do that here
            // we could also do something like response.isAnError()
            // and call another service that could talk to another endpoint on another server
            // but for now we will just deliver the data
            return response.toDomain()
        } catch (e: Exception) {
            // there is already a resource error handling inside the Resource class so that code appears to not be useful
            // it is here in case  some code done after the first call is done directly with the suspend
            // and fail that way we have a wrapper over all backend call to handle that case
            return Resource.Error()
        }
    }
}