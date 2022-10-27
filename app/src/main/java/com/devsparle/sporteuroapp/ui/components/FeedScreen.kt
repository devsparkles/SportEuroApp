package com.devsparle.sporteuroapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsparle.sporteuroapp.R
import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.presentation.screens.feed_home.FeedViewModel
import com.devsparle.sporteuroapp.ui.theme.DarkBlue
import com.devsparle.sporteuroapp.utils.LogApp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel(),
    onStoryClicked: (FeedItem.Story) -> Unit,
    onVideoClicked: (FeedItem.Video) -> Unit
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
            if (loading!= null && loading == true) {
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
                    onVideoClicked = {

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
private fun ColumnScope.PostList(
    posts: MutableList<FeedItem>?,
    onStoryClicked: (FeedItem.Story) -> Unit,
    onVideoClicked: (String) -> Unit
) {
    if(posts.isNullOrEmpty()) {
        Text("Liste vide")
    } else {
        LogApp.d("Yo Xavier")
        //Text("Liste existance")
        LazyColumn() {
            items(posts){ post->
                when (post) {
                    is FeedItem.Story -> LogApp.d("Xavier story ${post.title}")
                    is FeedItem.Video ->  LogApp.d("Xavier video ${post.title}")
                }
            }
        }
    }

}
