package com.msg.design_system.component.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalInfiniteLoopPager(
    modifier: Modifier = Modifier,
    list: List<@Composable () -> Unit>
) {
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })

    LaunchedEffect(true) {
        var initialPage = Int.MAX_VALUE / 2

        while (initialPage % list.size != 0) {
            initialPage++
        }
        pagerState.scrollToPage(initialPage)
    }
    
    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill
        ) {
            list.getOrNull(it % (list.size))
        }
        PagerIndicator(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp),
            count = list.size,
            currentPage = pagerState.currentPage % list.size
        )
    }
}