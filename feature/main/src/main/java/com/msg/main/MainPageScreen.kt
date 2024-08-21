package com.msg.main

import android.app.Activity
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.common.event.Event
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
import com.msg.main.viewmodel.MainViewModel
import com.msg.model.entity.company.GetCompanyListEntity
import com.msg.model.entity.government.GetGovernmentEntity
import com.msg.model.entity.school.GetSchoolListEntity
import com.msg.model.entity.university.GetUniversityEntity
import com.msg.model.enumdata.Field
import com.msg.model.model.school.SchoolModel
import com.msg.ui.DevicePreviews
import java.util.UUID
import com.msg.model.entity.faq.GetFrequentlyAskedQuestionDetailEntity as GetFAQDetailEntity

@Composable
internal fun MainPageScreenRoute(
    viewModel: MainViewModel = hiltViewModel(),
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
        getSchoolList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.highSchoolList.value = it
            }
        )
        getUniversityList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.universityList.value = it
            }
        )
        getCompanyList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.companyList.value = it
            }
        )
        getGovernmentList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.governmentList.value = it
            }
        )
    }

    MainPageScreen(
        data = viewModel.faqList,
        highSchoolList = viewModel.highSchoolList.value,
        universityList = viewModel.universityList.value,
        companyList = viewModel.companyList.value,
        governmentList = viewModel.governmentList.value,
        event = error,
        role = role,
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
    viewModel: MainViewModel,
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

private suspend fun getSchoolList(
    viewModel: MainViewModel,
    onSuccess: (data: GetSchoolListEntity) -> Unit,
) {
    viewModel.getSchoolListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> {}
        }
    }
}

private suspend fun getUniversityList(
    viewModel: MainViewModel,
    onSuccess: (data: GetUniversityEntity) -> Unit
) {
    viewModel.getUniversityListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> {}
        }
    }
}

private suspend fun getCompanyList(
    viewModel: MainViewModel,
    onSuccess: (data: GetCompanyListEntity) -> Unit
) {
    viewModel.getCompanyListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> {}
        }
    }
}

private suspend fun getGovernmentList(
    viewModel: MainViewModel,
    onSuccess: (data: GetGovernmentEntity) -> Unit
) {
    viewModel.getGovernmentListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> {}
        }
    }
}

@Composable
internal fun MainPageScreen(
    modifier: Modifier = Modifier,
    data: List<GetFAQDetailEntity>,
    highSchoolList: GetSchoolListEntity,
    universityList: GetUniversityEntity,
    companyList: GetCompanyListEntity,
    governmentList: GetGovernmentEntity,
    event: Event<List<GetFAQDetailEntity>>,
    role: String,
    onAddClicked: (question: String, answer: String) -> Unit,
    onDialogButtonClicked: () -> Unit
) {
    val scrollState = rememberScrollState()
    val highSchoolScrollState = rememberScrollState()

    val highSchoolDoingList = listOf("교육과정_운영", "진로_지도", "학생_관리")
    val collegeDoingList = listOf("기업연계교육", "심화교육", "후학습지원")
    val enterpriseDoingList = listOf("현장맞춤형교육", "현장실습", "고졸채용")
    val governmentDoingList = listOf("산업인력_분석", "특화프로그램_운영", "고졸채용네트워크_구축")

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
                        title = "직업계고",
                        icon = "\uD83C\uDFEB",
                        contentList = highSchoolDoingList
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "지역대학",
                        icon = "\uD83C\uDF93",
                        contentList = collegeDoingList
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "지역기업",
                        icon = "\uD83C\uDFE2",
                        contentList = enterpriseDoingList
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    BitgoeulInfoCardView(
                        modifier = modifier,
                        title = "유관기관",
                        icon = "\uD83D\uDCBC",
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
                        highSchoolList.schools.forEach {
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
                    list = Field.entries
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
                    CollegeCardViewList(data = universityList)
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
                    list = Field.entries,
                    companyData = companyList
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
                    data = governmentList,
                    list = Field.entries
                )
                Spacer(modifier = modifier.height(64.dp))
                FaqSection(data = data)
                if (role == "ROLE_ADMIN") {
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
        highSchoolList = GetSchoolListEntity(
            listOf(SchoolModel(
                id = 0,
                schoolName = "광주소프트웨어마이스터고등학교",
                line = "",
                department = listOf(),
                logoImageUrl = "https://media.licdn.com/dms/image/C560BAQEV41Od9boKag/company-logo_200_200/0/1640911244845?e=2147483647&v=beta&t=Rl96EXJTFPLuPtUmzN6-Wa807_9xDnvEhkDvTCXgnlM",
                clubs = listOf(
                    SchoolModel.Club(
                        id = UUID.randomUUID(),
                        clubName = "devGsm",
                        field = Field.AI_CONVERGENCE.toString()
                    )
                )
            ))
        ),
        universityList = GetUniversityEntity(
            universities = listOf(
                GetUniversityEntity.University(
                    id = 0,
                    universityName = "송원대학교",
                    departments = listOf(
                        "철도운전관제시스템과",
                        "철도차량전기시스템과",
                        "미용예술학과",
                        "철도운전경영과"
                    )
                )
            )
        ),
        companyList = GetCompanyListEntity(
            companies = listOf(
                GetCompanyListEntity.Company(
                    id = 0,
                    companyName = "광주동물메디컬센터",
                    field = Field.CULTURE.toString()
                ),
                GetCompanyListEntity.Company(
                    id = 0,
                    companyName = "24시노아동물메디컬센터",
                    field = Field.CULTURE.toString()
                ),
                GetCompanyListEntity.Company(
                    id = 0,
                    companyName = "한국조경수협회",
                    field = Field.CULTURE.toString()
                )
            )
        ),
        governmentList = GetGovernmentEntity(
            governments = listOf(
                GetGovernmentEntity.Government(
                    id = 0,
                    field = Field.ENERGY.toString(),
                    governmentName = "에너지밸리기업개발원"
                )
            )
        ),
        onAddClicked = {_,_->},
        role = "ROLE_ADMIN",
        event = Event.Success(),
        onDialogButtonClicked = {}
    )
}