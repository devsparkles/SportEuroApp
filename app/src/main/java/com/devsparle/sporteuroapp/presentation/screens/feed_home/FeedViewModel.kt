package com.devsparle.sporteuroapp.presentation.screens.feed_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.devsparle.sporteuroapp.di.annotations.DefaultDispatcher
import com.devsparle.sporteuroapp.di.annotations.IoDispatcher
import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.domain.usecases.GetFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    val getFeedUseCase: GetFeedUseCase,
    val player: Player,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher,
) : ViewModel() {


    val _feedItems: MutableLiveData<MutableList<FeedItem>> =
        MutableLiveData(mutableListOf<FeedItem>())
    var feedItems: LiveData<MutableList<FeedItem>> = _feedItems

    val _loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading


    init {
        player.prepare()
        retrieveFeed()
    }

    private fun retrieveFeed() {
        viewModelScope.launch(ioDispatcher) {
            _loading.postValue(true)
            val feed = getFeedUseCase()
            _feedItems.postValue(feed)
            _loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}