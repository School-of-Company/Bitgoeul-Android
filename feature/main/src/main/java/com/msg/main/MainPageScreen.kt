package com.msg.main

import com.msg.model.enumdata.Authority
import android.app.Activity
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
import com.msg.design_system.R
import com.msg.design_system.component.dialog.BitgoeulAlertDialog
import com.msg.design_system.component.icon.GwangjuIcon
import com.msg.design_system.component.icon.OfficeOfEducationIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.main.component.AddFaqItem
import com.msg.main.component.BitgoeulInfoCardView
import com.msg.main.component.ClubInfoCardViewList
import com.msg.main.component.CollegeCardViewList
import com.msg.main.component.FaqSection
import com.msg.main.component.HighSchoolCardView
import com.msg.main.component.HorizontalInfiniteBannerLoopPager
import com.msg.main.component.HorizontalInfiniteLoopPager
import com.msg.main.viewmodel.FaqViewModel
import com.msg.model.enumdata.HighSchool
import com.msg.model.ui.CSTCollegeData
import com.msg.model.ui.DKCollegeData
import com.msg.model.ui.HNCollegeData
import com.msg.model.ui.NBCollegeData
import com.msg.model.ui.SWCollegeData
import com.msg.model.ui.SYCollegeData
import com.msg.ui.DevicePreviews
import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity as GetFAQDetailEntity

@Composable
internal fun MainPageScreenRoute(
    viewModel: FaqViewModel = hiltViewModel(),
    onLoginClicked: () -> Unit
) {
    val role = viewModel.role
    var error: Event<List<GetFAQDetailEntity>> = Event.Loading
    var isReLaunched = false
    val activity = LocalContext.current as Activity

    viewModel.getFaq()
    
    LaunchedEffect(true, isReLaunched) {
        getFaqList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.faqList.addAll(it)
                error = Event.Success()
            },
            onFailure = {
                error = it
            }
        )
    }

    MainPageScreen(
        data = viewModel.faqList,
        event = error,
        role = Authority.valueOf(role),
        onAddClicked = { question, answer ->
            viewModel.addFaq(
                question = question,
                answer = answer
            )
        },
        onDialogButtonClicked = {
            when (error) {
                is Event.Success -> {}
                is Event.Unauthorized -> onLoginClicked()
                is Event.BadRequest -> {
                    isReLaunched = !isReLaunched
                }
                else -> {
                    activity.finish()
                }
            }
        }
    )
}

private suspend fun getFaqList(
    viewModel: FaqViewModel,
    onSuccess: (data: List<GetFAQDetailEntity>) -> Unit,
    onFailure: (error: Event<List<GetFAQDetailEntity>>) -> Unit
) {
    viewModel.getFaqListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> {
                onFailure(response)
            }
        }
    }
}

@Composable
internal fun MainPageScreen(
    modifier: Modifier = Modifier,
    data: List<GetFAQDetailEntity>,
    event: Event<List<GetFAQDetailEntity>>,
    role: Authority,
    onAddClicked: (question: String, answer: String) -> Unit,
    onDialogButtonClicked: () -> Unit
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

    val questionValue = remember { mutableStateOf("") }
    val answerValue = remember { mutableStateOf("") }

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
                Image(
                    modifier = modifier
                        .height(332.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.mipmap.banner_main),
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
                Spacer(modifier = modifier.height(64.dp))
                Text(
                    modifier = modifier.padding(horizontal = 28.dp),
                    text = "유관기관 소개",
                    style = typography.titleMedium,
                    color = colors.BLACK
                )
                Text(
                    modifier = modifier.padding(horizontal = 28.dp),
                    text = "#혁신지구와_함께하는",
                    style = typography.labelMedium,
                    color = colors.G1
                )
                Spacer(modifier = modifier.height(24.dp))
                HorizontalInfiniteBannerLoopPager(
                    list = listOf(
                        "Medical",
                        "AI",
                        "Culture",
                        "Energy",
                        "FutureTransport"
                    )
                )
                Spacer(modifier = modifier.height(64.dp))
                FaqSection(data = data)
                if (role == Authority.ROLE_ADMIN) {
                    AddFaqItem(
                        questionValue = questionValue.value,
                        onQuestionValueChanged = {
                            questionValue.value = it
                        },
                        answerValue = answerValue.value,
                        onAnswerValueChanged = {
                            answerValue.value = it
                        },
                        onAddClicked = { onAddClicked(questionValue.value, answerValue.value) }
                    )
                }
                Spacer(modifier = modifier.height(24.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = colors.P2)
                        .padding(horizontal = 28.dp)
                ) {
                    Spacer(modifier = modifier.height(40.dp))
                    GwangjuIcon()
                    Spacer(modifier = modifier.height(16.dp))
                    OfficeOfEducationIcon()
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = "©2023 [Copyright Holder] All Rights Reserved.",
                        style = typography.labelMedium,
                        color = colors.WHITE
                    )
                    Spacer(modifier = modifier.height(140.dp))
                }
            }
            when (event) {
                is Event.Success -> {}
                is Event.Unauthorized -> {
                    BitgoeulAlertDialog(
                        title = "오류",
                        msg = "토큰이 만료되었습니다, 로그아웃 후 다시 로그인해주세요.",
                        onQuit = {},
                        onButtonClicked = onDialogButtonClicked
                    )
                }
                is Event.BadRequest -> {
                    BitgoeulAlertDialog(
                        title = "오류",
                        msg = "요청을 보내는 중 문제가 발생했습니다.",
                        onQuit = {},
                        onButtonClicked = onDialogButtonClicked
                    )
                }
                else -> {}
            }
        }
    }
}

@DevicePreviews
@Composable
fun MainPageScreenPre() {
    MainPageScreen(
        data = listOf(
            GetFAQDetailEntity(
                id = 0,
                question = "학원에서 자격증 과정을 운영할 수 있나요?",
                answer = "불가능 합니다. 그러나, 학교 주관으로 학원강사를 섭외할 수는 있고, 학원시설 이용비, 학원강사 수당 지급은 가능 합니다."
            )
        ),
        onAddClicked = {_,_->},
        role = Authority.ROLE_ADMIN,
        event = Event.Success(),
        onDialogButtonClicked = {}
    )
}