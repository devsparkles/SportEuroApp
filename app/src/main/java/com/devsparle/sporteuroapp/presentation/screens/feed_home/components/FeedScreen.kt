package com.devsparle.sporteuroapp.presentation.screens.feed_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsparle.sporteuroapp.R
import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.presentation.screens.feed_home.FeedViewModel
import com.devsparle.sporteuroapp.ui.theme.DarkBlue


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel(),
    onStoryClicked: (FeedItem.Story) -> Unit,
    onVideoClicked: (String) -> Unit
) {

    val loading by viewModel.loading.observeAsState()
    val posts by viewModel.feedItems.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray.copy(alpha = 0.5f))
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier

                .fillMaxSize()
        ) {
            HeaderBar()
            if (loading != null && loading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(30.dp),
                        color = Color.Blue,
                        strokeWidth = 3.dp
                    )
                }
            } else {
                PostList(
                    posts = posts,
                    onStoryClicked = {
                        onStoryClicked(it)
                    },
                    onVideoClicked = { e ->
                        onVideoClicked(e)
                    }
                )
            }
        }
    }
}


@Composable
private fun HeaderBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = DarkBlue)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(id = R.string.featured),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}


@Composable
private fun PostList(
    posts: MutableList<FeedItem>?,
    onStoryClicked: (FeedItem.Story) -> Unit,
    onVideoClicked: (String) -> Unit
) {
    if (posts.isNullOrEmpty()) {
        Text(
            text = stringResource(R.string.no_posts)
        )
    } else {
        LazyColumn {
            items(posts) { post ->
                when (post) {
                    is FeedItem.Story -> {
                        StoryItem(post) { story ->
                            onStoryClicked(story)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    is FeedItem.Video -> {
                        VideoItem(video = post) { url ->
                            onVideoClicked(url)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }

}
