package com.msg.main.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.pager.PagerIndicator
import com.msg.main.banner.club.*
import com.msg.main.banner.industry.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalInfiniteLoopPager(
    modifier: Modifier = Modifier,
    bannerType: String,
    list: List<String>
) {

    var initialPage = Int.MAX_VALUE / 2
        while (initialPage % list.size != 0) {
            initialPage++
        }

    val pagerState = rememberPagerState(
        pageCount = { Int.MAX_VALUE },
        initialPage = initialPage
    )
    
    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 10
        ) {
            when (bannerType) {
                "Club" -> {
                    when (list[it % (list.size)]) {
                        "Future" -> FutureTransportClubBanner()
                        "Energy" -> EnergyIndustryClubBanner()
                        "MedicalHealth" -> MedicalHealthClubBanner()
                        "AI" -> AiFusionAndIntegrationClubBanner()
                        "CultureIndustry" -> CultureIndustryClubBanner()
                    }
                }
                "Industry" -> {
                    when (list[it % (list.size)]) {
                        "Future" -> FutureTransportationBanner()
                        "Energy" -> EnergyIndustryBanner()
                        "MedicalHealth" -> MedicalHealthBanner()
                        "AI" -> AiFusionAndIntegrationBanner()
                        "CultureIndustry" -> CultureIndustryBanner()
                    }
                }
            }
        }
        PagerIndicator(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp),
            count = list.size,
            currentPage = pagerState.currentPage % list.size
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalInfiniteBannerLoopPager(
    modifier: Modifier = Modifier,
    list: List<String>
) {

    val size = 0.2f
    val secondSize = 0.5f
    var initialPage = Int.MAX_VALUE / 2
    while (initialPage % list.size != 0) {
        initialPage++
    }

    val pagerState = rememberPagerState(
        pageCount = { Int.MAX_VALUE },
        initialPage = initialPage
    )

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            modifier = modifier.align(Alignment.Center),
            state = pagerState,
            beyondBoundsPageCount = 10,
            pageSpacing = (-50).dp,
            contentPadding = PaddingValues(horizontal = 80.dp)
        ) {
            GovernmentBannerItem(
                modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - it) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        scaleX = 1f - (size * pageOffset)
                        scaleY = 1f - (size * pageOffset)
                    },
                field = list[it % list.size]
            )
        }
    }
}