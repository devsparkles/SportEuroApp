package com.devsparle.sporteuroapp.presentation.screens.videofullscreen

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoFullScreenViewModel @Inject constructor(
    val player: ExoPlayer
) : ViewModel() {
    init {
        player.prepare()
    }

    fun playVideo(url: String) {
        player.setMediaItem(MediaItem.fromUri(Uri.parse(url)))
        player.prepare()
        player.playWhenReady = true
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}