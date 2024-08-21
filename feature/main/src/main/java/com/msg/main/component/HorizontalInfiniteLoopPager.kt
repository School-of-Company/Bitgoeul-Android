package com.msg.main.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.msg.design_system.component.pager.PagerIndicator
import com.msg.model.entity.company.GetCompanyListEntity
import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.enumdata.Field
import com.msg.model.model.school.SchoolModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HorizontalInfiniteLoopPager(
    modifier: Modifier = Modifier,
    bannerType: String,
    list: List<Field>,
    companyData: GetCompanyListEntity? = null,
    schoolData: GetSchoolListEntity? = null
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
                    ClubBanner(data = schoolData ?: GetSchoolListEntity(listOf()), field = list[it % (list.size)])
                }
                "Industry" -> {
                    IndustryBanner(data = companyData?.getSpecificFieldList(list[it % (list.size)]) ?: listOf(), field = list[it % (list.size)])
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
    data: GetGovernmentEntity,
    list: List<Field>
) {

    val size = 0.2f
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
                modifier = modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - it) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        scaleX = 1f - (size * pageOffset)
                        scaleY = 1f - (size * pageOffset)
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                data = data.getSpecificFieldList(list[it % list.size]),
                field = list[it % list.size]
            )
        }
    }
}

fun GetCompanyListEntity.getSpecificFieldList(field: Field): List<String> {
    return companies.mapNotNull {
        if (it.field == field.toString()) it.companyName else null
    }
}

fun GetGovernmentEntity.getSpecificFieldList(field: Field): List<String> {
    return governments.mapNotNull {
        if (it.field == field.toString()) it.governmentName else null
    }
}

fun SchoolModel.getSpecificFieldList(field: Field): List<String> {
    return clubs.mapNotNull {
        if (it.field == field.toString()) it.clubName else null
    }
 }