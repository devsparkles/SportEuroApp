package com.devsparle.sporteuroapp.domain.repository.remote

import com.devsparle.sporteuroapp.domain.model.Feed
import com.devsparle.sporteuroapp.utils.resource.Resource

interface RemoteFeedRepository {

    suspend fun getFeed(): Resource<Feed?>
}