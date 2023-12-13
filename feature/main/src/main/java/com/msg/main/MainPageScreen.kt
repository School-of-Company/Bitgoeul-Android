package com.msg.main

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.msg.main.component.BitgoeulInfoCardView
import com.msg.main.component.ClubInfoCardViewList
import com.msg.main.component.CollegeCardViewList
import com.msg.main.component.HighSchoolCardView
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.main.component.HorizontalInfiniteLoopPager
import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.ui.CSTCollegeData
import com.msg.model.ui.DKCollegeData
import com.msg.model.ui.HNCollegeData
import com.msg.model.ui.NBCollegeData
import com.msg.model.ui.SWCollegeData
import com.msg.model.ui.SYCollegeData
import com.msg.ui.DevicePreviews

@Composable
fun MainPageScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val highSchoolScrollState = rememberScrollState()

    val highSchoolDoingList = listOf("교육과정 운영", "진로 지도", "학생 관리")
    val collegeDoingList = listOf("기업 연계 교육", "심화 교육", "후학습 지원")
    val enterpriseDoingList = listOf("현장 맞춤형 교육", "현장 실습", "고졸 채용")
    val governmentDoingList = listOf("산업 인력 분석", "특화프로그램 운영", "고졸채용네트워크 구축")

    val highSchoolList = HighSchool.values()
    val collegeList = listOf(
        SWCollegeData,
        HNCollegeData,
        CSTCollegeData,
        DKCollegeData,
        SYCollegeData,
        NBCollegeData
    )

    BitgoeulAndroidTheme { colors, typography ->
        Surface(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .background(color = colors.WHITE)
            ) {
                AsyncImage(
                    modifier = modifier
                        .height(332.dp)
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.mipmap.banner_main)
                        .build(),
                    contentDescription = "Banner image of main page",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.BottomCenter
                )
                Spacer(modifier = modifier.height(16.dp))
                Column(
                    modifier = modifier.padding(28.dp)
                ) {
                    Text(
                        text = "빛고을 직업교육 혁신지구",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Text(
                        text = "지역산업 발전을 위해 당신이 필요해요",
                        style = typography.labelMedium,
                        color = colors.G1
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "\uD83C\uDFEB 직업계고",
                        contentList = highSchoolDoingList
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "\uD83C\uDF93 지역대학",
                        contentList = collegeDoingList
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "\uD83C\uDFE2 지역기업",
                        contentList = enterpriseDoingList
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "\uD83D\uDCBC 유관기관",
                        contentList = governmentDoingList
                    )
                    Spacer(modifier = modifier.height(64.dp))
                    Text(
                        text = "직업계고 소개",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Text(
                        text = "직업계고 계열별 학교현황 및 진로",
                        style = typography.labelMedium,
                        color = colors.G1
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Row(
                        modifier = modifier.horizontalScroll(highSchoolScrollState),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        highSchoolList.forEach {
                            HighSchoolCardView(
                                modifier = modifier,
                                school = it
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(64.dp))
                    Text(
                        text = "취업동아리 소개",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Text(
                        text = "Sample Text",
                        style = typography.labelMedium,
                        color = colors.G1
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    ClubInfoCardViewList(
                        modifier = modifier,
                        data = ClubInfoList
                    )
                }
                HorizontalInfiniteLoopPager(
                    bannerType = "Club",
                    list = listOf(
                        "Future",
                        "Energy",
                        "MedicalHealth",
                        "AI",
                        "CultureIndustry"
                    )
                )
                Column(
                    modifier = modifier.padding(horizontal = 28.dp)
                ) {
                    Spacer(modifier = modifier.height(64.dp))
                    Text(
                        text = "연계 대학 소개",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Text(
                        text = "Sample Text",
                        style = typography.labelMedium,
                        color = colors.G1
                    )
                    CollegeCardViewList(data = collegeList)
                    Spacer(modifier = modifier.height(64.dp))
                    Text(
                        text = "참여 기업 소개",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Text(
                        text = "Sample Text",
                        style = typography.labelMedium,
                        color = colors.G1
                    )
                    Spacer(modifier = modifier.height(24.dp))
                }
                HorizontalInfiniteLoopPager(
                    bannerType = "Industry",
                    list = listOf(
                        "Future",
                        "Energy",
                        "MedicalHealth",
                        "AI",
                        "CultureIndustry"
                    )
                )
            }
        }
    }
}

@DevicePreviews
@Composable
fun MainPageScreenPre() {
    MainPageScreen()
}