package com.msg.sign_up

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.modifier.padding.paddingHorizontal
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.LockScreenOrientation
import com.msg.design_system.util.checkPasswordRegex
import com.msg.sign_up.component.SignUpBottomSheet
import com.msg.sign_up.data.BelongList
import com.msg.sign_up.data.HighSchoolList
import com.msg.sign_up.data.OutsideJobList
import com.msg.sign_up.data.SchoolJobList
import com.msg.sign_up.util.enums.Keyboard
import com.msg.sign_up.util.enums.SignUpState
import com.msg.sign_up.util.keyboardAsState
import com.msg.sign_up.util.searchClubBySchool
import com.msg.sign_up.util.searchingInList
import com.msg.sign_up.viewmodel.SignUpViewModel

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onBackClicked: () -> Unit,
    onEnterFinished: () -> Unit
) {
    val belongValue by viewModel.belong.collectAsStateWithLifecycle()
    val jobValue by viewModel.job.collectAsStateWithLifecycle()
    val schoolValue by viewModel.school.collectAsStateWithLifecycle()
    val clubValue by viewModel.club.collectAsStateWithLifecycle()
    val nameValue by viewModel.name.collectAsStateWithLifecycle()
    val phoneNumberValue by viewModel.phoneNumber.collectAsStateWithLifecycle()
    val collegeValue by viewModel.college.collectAsStateWithLifecycle()
    val enrollmentValue by viewModel.enrollment.collectAsStateWithLifecycle()
    val enterpriseValue by viewModel.enterprise.collectAsStateWithLifecycle()
    val governmentValue by viewModel.government.collectAsStateWithLifecycle()
    val gradeAndNumberValue by viewModel.gradeAndNumber.collectAsStateWithLifecycle()
    val positionValue by viewModel.position.collectAsStateWithLifecycle()
    val sectorsValue by viewModel.sectors.collectAsStateWithLifecycle()
    val emailValue by viewModel.email.collectAsStateWithLifecycle()
    val passwordValue by viewModel.password.collectAsStateWithLifecycle()
    val rePasswordValue by viewModel.rePassword.collectAsStateWithLifecycle()

    val outsideJobListForSearch = OutsideJobList
    val schoolJobListForSearch = SchoolJobList
    var belongListForSearch by rememberSaveable { mutableStateOf(BelongList) }

    SignUpScreen(
        belong = belongValue,
        job = jobValue,
        school = schoolValue,
        club = clubValue,
        name = nameValue,
        phoneNumber = phoneNumberValue,
        college = collegeValue,
        enrollment = enrollmentValue,
        enterprise = enterpriseValue,
        government = governmentValue,
        gradeAndNumber = gradeAndNumberValue,
        position = positionValue,
        sectors = sectorsValue,
        email = emailValue,
        password = passwordValue,
        rePassword = rePasswordValue,
        onBelongChange = viewModel::onBelongChange,
        onJobChange = viewModel::onJobChange,
        onSchoolChange = viewModel::onSchoolChange,
        onClubChange = viewModel::onClubChange,
        onNameChange = viewModel::onNameChange,
        onPhoneNumberChange = viewModel::onPhoneNumberChange,
        onCollegeChange = viewModel::onCollegeChange,
        onEnrollmentChange = viewModel::onEnrollmentChange,
        onEnterpriseChange = viewModel::onEnterpriseChange,
        onGovernmentChange = viewModel::onGovernmentChange,
        onGradeAndNumberChange = viewModel::onGradeAndNumberChange,
        onPositionChange = viewModel::onPositionChange,
        onSectorsChange = viewModel::onSectorsChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRePasswordChange = viewModel::onRePasswordChange,
        onBackClicked = onBackClicked,
        outsideJobListForSearch = outsideJobListForSearch,
        schoolJobListForSearch = schoolJobListForSearch,
        belongListForSearch = belongListForSearch,
        onBelongListForSearchChange = { newBelongList ->
            belongListForSearch = newBelongList
        },
        onEnterFinished = { job, school, club, name, phoneNumber, college, enrollment, enterprise, government, gradeAndNumber, email, password, position, sectors ->
            viewModel.onJobChange(job)
            viewModel.onSchoolChange(school)
            viewModel.onClubChange(club)
            viewModel.onNameChange(name)
            viewModel.onPhoneNumberChange(phoneNumber)
            viewModel.onCollegeChange(college)
            viewModel.onEnrollmentChange(enrollment)
            viewModel.onEnterpriseChange(enterprise)
            viewModel.onGovernmentChange(government)
            viewModel.onGradeAndNumberChange(gradeAndNumber)
            viewModel.onPositionChange(position)
            viewModel.onSectorsChange(sectors)
            viewModel.onEmailChange(email)
            viewModel.onPasswordChange(password)
            viewModel.signUp()
            onEnterFinished()
        }
    )
}

@Composable
internal fun SignUpScreen(
    modifier: Modifier = Modifier,
    belong: String,
    job: String,
    school: String,
    club: String,
    name: String,
    phoneNumber: String,
    college: String,
    enrollment: Int,
    enterprise: String,
    government: String,
    gradeAndNumber: String,
    position: String,
    sectors: String,
    email: String,
    password: String,
    rePassword: String,
    onBelongChange: (String) -> Unit,
    onJobChange: (String) -> Unit,
    onSchoolChange: (String) -> Unit,
    onClubChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onCollegeChange: (String) -> Unit,
    onEnrollmentChange: (Int) -> Unit,
    onEnterpriseChange: (String) -> Unit,
    onGovernmentChange: (String) -> Unit,
    onGradeAndNumberChange: (String) -> Unit,
    onPositionChange: (String) -> Unit,
    onSectorsChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRePasswordChange: (String) -> Unit,
    onBackClicked: () -> Unit,
    outsideJobListForSearch: List<String>,
    schoolJobListForSearch: List<String>,
    belongListForSearch: List<String>,
    onBelongListForSearchChange: (List<String>) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    scrollState: ScrollState = rememberScrollState(),
    onEnterFinished: (
        job: String,
        school: String,
        club: String,
        name: String,
        phoneNumber: String,
        college: String,
        enrollment: Int,
        enterprise: String,
        government: String,
        gradeAndNumber: String,
        email: String,
        password: String,
        position: String,
        sectors: String
    ) -> Unit,
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val (isSignUpState, isSetSignUpState) = rememberSaveable { mutableStateOf(SignUpState.Belong) }

    val (isShowSignUpBottomSheet, isSetShowSignUpBottomSheet) = rememberSaveable { mutableStateOf(false) }
    val (isShowJobTextField, isSetShowJobTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowSchoolTextField, isSetShowSchoolTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowClubTextField, isSetShowClubTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowNameTextField, isSetShowNameTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowCollegeTextField, isSetShowCollegeTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowEnterpriseTextField, isSetShowEnterpriseTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowGovernmentTextField, isSetShowGovernmentTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowEnrollmentTextField, isSetShowEnrollmentTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowGradeAndNumberTextField, isSetShowGradeAndNumberTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowPositionTextField, isSetShowPositionTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowSectorsTextField, isSetShowSectorsTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowPhoneNumberTextField, isSetShowPhoneNumberTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowEmailTextField, isSetShowEmailTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowPasswordTextField, isSetShowPasswordTextField) = rememberSaveable { mutableStateOf(false) }
    val (isShowRePasswordTextField, isSetShowRePasswordTextField) = rememberSaveable { mutableStateOf(false) }

    val isLoading by rememberSaveable { mutableStateOf(false) }

    val (isSelectedBelong, isSetSelectedBelong) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedJob, isSetSelectedJob) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedSchool, isSetSelectedSchool) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedClub, isSetSelectedClub) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedName, isSetSelectedName) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedPhoneNumber, isSetSelectedPhoneNumber) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedCollege, isSetSelectedCollege) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedEnrollment, isSetSelectedEnrollment) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedEnterprise, isSetSelectedEnterprise) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedGovernment, isSetSelectedGovernment) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedGradeAndNumber, isSetSelectedGradeAndNumber) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedPosition, isSetSelectedPosition) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedSectors, isSetSelectedSectors) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedEmail, isSetSelectedEmail) = rememberSaveable { mutableStateOf(false) }
    val (isSelectedPassword, isSetSelectedPassword) = rememberSaveable { mutableStateOf(false) }
    var isSelectedRePassword by rememberSaveable { mutableStateOf(false) }
    var isActivatedBeforePhoneNumber by rememberSaveable { mutableStateOf(false) }

    val (isSchoolListForSearch, isSetSchoolListForSearch) = rememberSaveable { mutableStateOf(HighSchoolList) }
    val (isClubListForSearch, isSetClubListForSearch) = rememberSaveable { mutableStateOf(school.searchClubBySchool()) }

    val (isApplicationButtonEnabled, isSetApplicationButtonEnabled) = rememberSaveable { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            Column(
                modifier = modifier
                    .background(colors.WHITE)
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기",
                    onClicked = onBackClicked
                )
                Column(modifier = modifier.fillMaxWidth()) {
                    Spacer(modifier = modifier.height(16.dp))
                    when (isSignUpState) {
                        SignUpState.Belong -> {
                            if (isSelectedBelong) {
                                isSetShowJobTextField(false)
                                onJobChange("")
                            }
                            Text(
                                text = "만나서 반가워요!",
                                style = typography.titleLarge,
                                color = colors.BLACK
                            )
                            Text(
                                text = "어디서 오셨나요?",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Job -> {
                            if (isSelectedJob) {
                                isSetShowSchoolTextField(false)
                                onSchoolChange("")
                            }
                            isSetShowJobTextField(true)
                            Text(
                                text = "만나서 반가워요!",
                                style = typography.titleLarge,
                                color = colors.BLACK
                            )
                            Text(
                                text = "어디서 오셨나요?",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.School -> {
                            if (isSelectedSchool) {
                                isSetShowClubTextField(false)
                                onClubChange("")
                            }
                            isSetShowSchoolTextField(true)
                            Text(
                                text = "학교 선택",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "재학 중이신 학교를 선택해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Club -> {
                            if (isSelectedClub) {
                                isSetShowNameTextField(false)
                                onNameChange("")
                            }
                            isSetShowClubTextField(true)
                            Text(
                                text = "동아리 선택",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "가입하신 동아리를 선택해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Name -> {
                            if (isSelectedName) {
                                isSetShowPhoneNumberTextField(false)
                                isSetShowCollegeTextField(false)
                                isSetShowEnrollmentTextField(false)
                                isSetShowEnterpriseTextField(false)
                                isSetShowGovernmentTextField(false)
                                onCollegeChange("")
                                onEnrollmentChange(0)
                                onEnterpriseChange("")
                                onGovernmentChange("")
                            }
                            isSetShowNameTextField(true)
                            Text(
                                text = "이름 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "이름을 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.College -> {
                            if (isSelectedCollege) {
                                isSetShowPhoneNumberTextField(false)
                                onPhoneNumberChange("")
                            }
                            isSetShowCollegeTextField(true)
                            Text(
                                text = "대학 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "소속되신 대학을 입력해 주세요",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Enterprise -> {
                            if (isSelectedEnterprise) {
                                isSetShowPhoneNumberTextField(false)
                                onPhoneNumberChange("")
                            }
                            isSetShowEnterpriseTextField(true)
                            Text(
                                text = "기업 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "소속되신 기업을 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Government -> {
                            if (isSelectedGovernment) {
                                isSetShowPositionTextField(false)
                                onSectorsChange("")
                            }
                            isSetShowGovernmentTextField(true)
                            Text(
                                text = "기관입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "어디서 오셨나요?",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Enrollment -> {
                            if (isSelectedEnrollment) {
                                isSetShowPhoneNumberTextField(false)
                                onGradeAndNumberChange("")
                            }
                            isSetShowEnrollmentTextField(true)
                            Text(
                                text = "입학년도 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "입학하신 연도를 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.GradeAndNumber -> {
                            if (isSelectedGradeAndNumber) {
                                isSetShowPhoneNumberTextField(false)
                                onPhoneNumberChange("")
                            }
                            isSetShowGradeAndNumberTextField(true)
                            Text(
                                text = "학번 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "학년, 반, 번호를 입력해 주세요! (ex:1101)",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Position -> {
                            if (isSelectedPosition) {
                                isSetShowSectorsTextField(false)
                                onPositionChange("")
                            }
                            isSetShowPositionTextField(true)
                            Text(
                                text = "업종 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "소속하신 기관의 업종을 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Sectors -> {
                            if (isSelectedSectors) {
                                isSetShowPhoneNumberTextField(false)
                                onPhoneNumberChange("")
                            }
                            isSetShowSectorsTextField(true)
                            Text(
                                text = "직책 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "본인의 직책을 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.PhoneNumber -> {
                            if (isSelectedPhoneNumber) {
                                isSetShowEmailTextField(false)
                                onEmailChange("")
                            }
                            isSetShowPhoneNumberTextField(true)
                            Text(
                                text = "전화번호 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "전화번호를 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Email -> {
                            if (isSelectedEmail) {
                                isSetShowPasswordTextField(false)
                                onPasswordChange("")
                            }
                            isSetShowEmailTextField(true)
                            Text(
                                text = "이메일 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "이메일을 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Password -> {
                            if (isSelectedPassword) {
                                isSetShowRePasswordTextField(false)
                                onRePasswordChange("")
                                isSetApplicationButtonEnabled(false)
                            }
                            isSetShowPasswordTextField(true)
                            Text(
                                text = "비밀번호 입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "8~24자 이내의 영문 / 숫자, 특수문자 1개 이상",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.RePassword -> {
                            isSetShowRePasswordTextField(true)
                            Text(
                                text = "비밀번호 재입력",
                                style = typography.titleLarge,
                                color = colors.G2
                            )
                            Text(
                                text = "비밀번호를 다시 입력해 주세요!",
                                style = typography.bodySmall,
                                color = colors.G2
                            )
                        }

                        SignUpState.Loading ->
                            if (password == rePassword) { isSetApplicationButtonEnabled(true) }
                    }
                    Spacer(modifier = modifier.height(32.dp))
                    Column(
                        modifier = modifier.verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (isShowRePasswordTextField && isShowPasswordTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSelectedRePassword = false
                            PasswordTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked && (password == rePassword))
                                            isSetSignUpState(
                                                continueToNextField(
                                                    isSignUpState,
                                                    job
                                                )
                                            )
                                    },
                                placeholder = "비밀번호",
                                errorText = "비밀번호가 일치하지 않습니다",
                                onValueChange = onRePasswordChange,
                                onLinkClicked = {},
                                isError = password == rePassword,
                                isLinked = false,
                                isDisabled = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.RePassword) isSetSignUpState(SignUpState.RePassword)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowPasswordTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedPassword(true)
                            PasswordTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked)
                                            isSetSignUpState(
                                                continueToNextField(
                                                    isSignUpState,
                                                    job
                                                )
                                            )
                                    },
                                placeholder = "비밀번호",
                                errorText = "비밀번호는 규칙에 맞게 입력해주세요",
                                onValueChange = onPasswordChange,
                                onLinkClicked = {},
                                isError = !password.checkPasswordRegex(),
                                isLinked = false,
                                isDisabled = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Password) isSetSignUpState(SignUpState.Password)
                                    isSetShowRePasswordTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowEmailTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedEmail(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked)
                                            isSetSignUpState(
                                                continueToNextField(
                                                    isSignUpState,
                                                    job
                                                )
                                            )
                                    },
                                placeholder = "이메일",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onEmailChange,
                                onButtonClicked = { onEmailChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Email) isSetSignUpState(SignUpState.Email)
                                    isSetShowPasswordTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowPhoneNumberTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedPhoneNumber(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "전화번호",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onPhoneNumberChange,
                                onButtonClicked = { onPhoneNumberChange("") },
                                isReadOnly = false,
                                isNumberOnly = true,
                                onClicked = {
                                    if (isSignUpState != SignUpState.PhoneNumber) isSetSignUpState(SignUpState.PhoneNumber)
                                    isSetShowEmailTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowGradeAndNumberTextField && isShowEnrollmentTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedGradeAndNumber(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "학번",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onGradeAndNumberChange,
                                onButtonClicked = { onGradeAndNumberChange("") },
                                isReadOnly = false,
                                isNumberOnly = true,
                                onClicked = {
                                    if (isSignUpState != SignUpState.GradeAndNumber) isSetSignUpState(SignUpState.GradeAndNumber)
                                    isActivatedBeforePhoneNumber = true
                                    isSetShowPhoneNumberTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowSectorsTextField && isShowPositionTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedSectors(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "직책 입력",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onPositionChange,
                                onButtonClicked = { onPositionChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Sectors) isSetSignUpState(SignUpState.Sectors)
                                    isActivatedBeforePhoneNumber = true
                                    isSetShowPhoneNumberTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowPositionTextField && isShowGovernmentTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedPosition(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "소속 기관의 업종",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onSectorsChange,
                                onButtonClicked = { onSectorsChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Position) isSetSignUpState(
                                        SignUpState.Position
                                    )
                                    isSetShowSectorsTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowGovernmentTextField && isShowNameTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedGovernment(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "소속 기관명",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onGovernmentChange,
                                onButtonClicked = { onGovernmentChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Government) isSetSignUpState(SignUpState.Government)
                                    isActivatedBeforePhoneNumber = true
                                    isSetShowPhoneNumberTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowCollegeTextField && isShowNameTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedCollege(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "소속 대학명",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onCollegeChange,
                                onButtonClicked = { onCollegeChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.College) isSetSignUpState(SignUpState.College)
                                    isActivatedBeforePhoneNumber = true
                                    isSetShowPhoneNumberTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowEnterpriseTextField && isShowNameTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedEnterprise(false)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "소속 기업명",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = onEnterpriseChange,
                                onButtonClicked = { onEnterpriseChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Enterprise) isSetSignUpState(SignUpState.Enterprise)
                                    isActivatedBeforePhoneNumber = true
                                    isSetShowPhoneNumberTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowEnrollmentTextField && isShowNameTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedEnrollment(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "입학년도",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = { onEnrollmentChange.toString() },
                                onButtonClicked = { onEnrollmentChange(0) },
                                isReadOnly = false,
                                isNumberOnly = true,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Enrollment) isSetSignUpState(
                                        SignUpState.Enrollment
                                    )
                                    isSetShowGradeAndNumberTextField(false)
                                    isSetClicked(true)
                                }
                            )
                        }
                        if (isShowNameTextField && isShowClubTextField) {
                            val (isClicked, isSetClicked) = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) { focusManager.clearFocus() }
                            isSetSelectedName(true)
                            DefaultTextField(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked) isSetSignUpState(
                                            continueToNextField(isSignUpState, job)
                                        )
                                    },
                                placeholder = "이름",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    onNameChange(it)
                                    if (it.length == 3) continueToNextField(
                                        isSignUpState,
                                        job
                                    )
                                },
                                onButtonClicked = { onNameChange("") },
                                isReadOnly = false,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Name) isSetSignUpState(SignUpState.Name)
                                    if (job == "취업동아리 선생님") isActivatedBeforePhoneNumber = true
                                    isSetClicked(true)
                                    isSetShowEnrollmentTextField(false)
                                    isSetShowPhoneNumberTextField(false)
                                    isSetShowEnterpriseTextField(false)
                                    isSetShowCollegeTextField(false)
                                    isSetShowGovernmentTextField(false)
                                }
                            )
                        }
                        if (isShowClubTextField && isShowSchoolTextField) {
                            isSetClubListForSearch(school.searchClubBySchool())
                            isSetSelectedClub(true)
                            DefaultTextField(
                                modifier = modifier.fillMaxWidth(),
                                placeholder = "동아리",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = true,
                                errorText = "",
                                onValueChange = {},
                                onButtonClicked = { onClubChange("") },
                                isReadOnly = true,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Club) isSetSignUpState(SignUpState.Club)
                                    isSetShowSignUpBottomSheet(true)
                                    isSetShowNameTextField(false)
                                },
                                value = club
                            )
                        }
                        if (isShowSchoolTextField && isShowJobTextField) {
                            isSetSelectedSchool(true)
                            DefaultTextField(
                                modifier = modifier.fillMaxWidth(),
                                placeholder = "학교",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = true,
                                errorText = "",
                                onValueChange = {},
                                onButtonClicked = { onSchoolChange("") },
                                isReadOnly = true,
                                onClicked = {
                                    if (isSignUpState != SignUpState.School) isSetSignUpState(SignUpState.School)
                                    isSetShowSignUpBottomSheet(true)
                                    isSetShowClubTextField(false)
                                },
                                value = school
                            )
                        }
                        if (isShowJobTextField) {
                            isSetSelectedJob(true)
                            DefaultTextField(
                                modifier = modifier.fillMaxWidth(),
                                placeholder = "직업",
                                isError = false,
                                isLinked = false,
                                isDisabled = isLoading,
                                isReverseTrailingIcon = true,
                                errorText = "",
                                onValueChange = {},
                                onButtonClicked = { onJobChange("") },
                                isReadOnly = true,
                                onClicked = {
                                    if (isSignUpState != SignUpState.Job) isSetSignUpState(SignUpState.Job)
                                    isSetShowSignUpBottomSheet(true)
                                    isSetShowSchoolTextField(false)
                                },
                                value = job
                            )
                        }
                        DefaultTextField(
                            modifier = modifier.fillMaxWidth(),
                            placeholder = "소속",
                            isError = false,
                            isLinked = false,
                            isDisabled = isLoading,
                            errorText = "",
                            onValueChange = {},
                            onButtonClicked = { onBelongChange("") },
                            isReadOnly = true,
                            isReverseTrailingIcon = true,
                            onClicked = {
                                if (isSignUpState != SignUpState.Belong) isSetSignUpState(SignUpState.Belong)
                                isSetShowSignUpBottomSheet(true)
                            },
                            value = belong
                        )
                    }
                }
            }
            if (isShowSignUpBottomSheet) {
                when (isSignUpState) {
                    SignUpState.Belong -> {
                        SignUpBottomSheet(
                            list = belongListForSearch,
                            selectedItem = belong,
                            onSelectedItemChanged = { if (belong != it) onBelongChange(it) else onBelongChange("") },
                            onQuit = {
                                isSetShowSignUpBottomSheet(false)
                                focusManager.clearFocus()
                                if (belong.isNotEmpty()) isSetSignUpState(continueToNextField(isSignUpState, belong))
                                isSetSelectedBelong(true)
                                isSetShowJobTextField(true)
                            },
                            isSearching = false,
                            onValueChanged = { onBelongListForSearchChange(searchingInList(it, BelongList)) }
                        ) {}
                    }

                    SignUpState.Job -> {
                        SignUpBottomSheet(
                            list = if (belong == "학교") schoolJobListForSearch else outsideJobListForSearch,
                            selectedItem = job,
                            isSearching = false,
                            onSelectedItemChanged = { if (job != it) onJobChange(it) else onJobChange("") },
                            onQuit = {
                                isSetShowSignUpBottomSheet(false)
                                focusManager.clearFocus()
                                isSetSignUpState(continueToNextField(isSignUpState, job))
                            },
                            onValueChanged = {}
                        ) {}
                    }

                    SignUpState.School -> {
                        SignUpBottomSheet(
                            list = isSchoolListForSearch,
                            selectedItem = school,
                            isSearching = true,
                            onSelectedItemChanged = { if (school != it) onSchoolChange(it) else onSchoolChange("") },
                            onQuit = {
                                isSetShowSignUpBottomSheet(false)
                                focusManager.clearFocus()
                                isSetSignUpState(continueToNextField(isSignUpState, job))
                            },
                            onValueChanged = { isSetSchoolListForSearch(searchingInList(it, HighSchoolList)) }
                        ) {}
                    }

                    SignUpState.Club -> {
                        SignUpBottomSheet(
                            list = isClubListForSearch,
                            selectedItem = club,
                            isSearching = true,
                            onSelectedItemChanged = { if (club != it) onClubChange(it) else onClubChange("") },
                            onQuit = {
                                isSetShowSignUpBottomSheet(false)
                                focusManager.clearFocus()
                                isSetSignUpState(continueToNextField(isSignUpState, job))
                            },
                            onValueChanged = { isSetClubListForSearch(searchingInList(it, school.searchClubBySchool())) }
                        ) {}
                    }

                    else -> {
                        isSetShowSignUpBottomSheet(false)
                    }
                }
            }
            BitgoeulButton(
                modifier = modifier
                    .fillMaxWidth()
                    .paddingHorizontal(horizontal = 28.dp, bottom = 16.dp)
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter),
                text = "회원가입 신청하기",
                state = if (isApplicationButtonEnabled) ButtonState.Enable else ButtonState.Disable,
                onClicked = {
                    onEnterFinished(
                        job,
                        school,
                        club,
                        name,
                        phoneNumber,
                        college,
                        enrollment,
                        enterprise,
                        government,
                        gradeAndNumber,
                        email,
                        password,
                        position,
                        sectors
                    )
                }
            )
        }
    }
}

fun continueToNextField(
    signUpState: SignUpState,
    signUpInfo: String
): SignUpState {
    when (signUpState) {
        SignUpState.Belong -> return SignUpState.Job
        SignUpState.Job -> return SignUpState.School
        SignUpState.School -> return SignUpState.Club
        SignUpState.Club -> return SignUpState.Name
        SignUpState.Name -> {
            when (signUpInfo) {
                "학생" -> return SignUpState.Enrollment
                "취업동아리 선생님" -> return SignUpState.PhoneNumber
                "기업강사" -> return SignUpState.Enterprise
                "대학교수" -> return SignUpState.College
                "유관기관" -> return SignUpState.Government
                "뽀짝 선생님" -> return SignUpState.Government
            }
        }

        SignUpState.College -> return SignUpState.PhoneNumber
        SignUpState.Enterprise -> return SignUpState.PhoneNumber
        SignUpState.Government -> return SignUpState.Position
        SignUpState.Enrollment -> { return SignUpState.GradeAndNumber }
        SignUpState.GradeAndNumber -> return SignUpState.PhoneNumber
        SignUpState.Position -> return SignUpState.Sectors
        SignUpState.Sectors -> return SignUpState.PhoneNumber
        SignUpState.Password -> return SignUpState.RePassword
        SignUpState.RePassword -> return SignUpState.Loading
        SignUpState.Loading -> return SignUpState.Loading
        SignUpState.PhoneNumber -> return SignUpState.Email
        SignUpState.Email -> return SignUpState.Password
    }
    return SignUpState.Loading
}

@Preview
@Composable
fun SignUpScreenPre() {
    SignUpScreen(
        onBackClicked = {},
        onEnterFinished = { _, _, _, _, _, _, _, _, _, _, _, _, _, _-> },
        onBelongChange = {},
        belong = "",
        onJobChange = {},
        job = "",
        onSchoolChange = {},
        school = "",
        onClubChange = {},
        club = "",
        onNameChange = {},
        name = "",
        onPhoneNumberChange = {},
        phoneNumber = "",
        onCollegeChange = {},
        college = "",
        onEnrollmentChange = {},
        enrollment = 0,
        onEnterpriseChange = {},
        enterprise = "",
        onGovernmentChange = {},
        government = "",
        onGradeAndNumberChange = {},
        gradeAndNumber = "",
        onPositionChange = {},
        position = "",
        onPasswordChange = {},
        password = "",
        onRePasswordChange = {},
        rePassword = "",
        onSectorsChange = {},
        sectors = "",
        onEmailChange = {},
        email = "",
        outsideJobListForSearch = listOf(),
        schoolJobListForSearch = listOf(),
        belongListForSearch = listOf(),
        onBelongListForSearchChange = {}
    )
}