package com.devsparle.sporteuroapp.presentation.screens.feed_home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.devsparle.sporteuroapp.domain.model.FeedItem

@Composable
fun StoryItem(
    story: FeedItem.Story,
    onStoryClicked: (FeedItem.Story) -> Unit
) {
    ArticleItem(
        cardBox = {
            Box(
                modifier = Modifier
                    .matchParentSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(story.imageUrl)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = story.title
                )
            }
        },
        tag = story.sport.name,
        title = story.title,
        subTitle = "By ${story.author} - ${story.humanTimeAgo}",
        onCardClick = {
            onStoryClicked(story)
        }
    )
}