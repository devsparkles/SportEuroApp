package com.devsparle.sporteuroapp.data.remote.feed.service

import com.devsparle.sporteuroapp.data.remote.feed.dto.FeedDto
import retrofit2.http.GET

interface FeedService {

    @GET("/api/json-storage/bin/edfefba")
    suspend fun getFeedInfo(): FeedDto?

}