package com.example.main.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.main.banner.AiFusionAndIntegrationBanner
import com.example.main.banner.CultureIndustryBanner
import com.example.main.banner.EnergyIndustryBanner
import com.example.main.banner.FutureTransportBanner
import com.example.main.banner.MedicalHealthBanner
import com.msg.design_system.component.pager.PagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalInfiniteLoopPager(
    modifier: Modifier = Modifier,
    bannerType: String,
    list: List<String>
) {
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
    
    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState
        ) {
            when (bannerType) {
                "Club" -> {
                    when (list[it % (list.size)]) {
                        "Future" -> FutureTransportBanner()
                        "Energy" -> EnergyIndustryBanner()
                        "MedicalHealth" -> MedicalHealthBanner()
                        "AI" -> AiFusionAndIntegrationBanner()
                        "CultureIndustry" -> CultureIndustryBanner()
                    }
                }
                "Industry" -> {}
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