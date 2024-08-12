package com.msg.sign_up

import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.icon.GoBackIcon
import com.msg.design_system.component.textfield.DefaultTextField
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.component.topbar.GoBackTopBar
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.LockScreenOrientation
import com.msg.sign_up.SignUpState.Belong
import com.msg.sign_up.SignUpState.Club
import com.msg.sign_up.SignUpState.College
import com.msg.sign_up.SignUpState.Email
import com.msg.sign_up.SignUpState.Enrollment
import com.msg.sign_up.SignUpState.Enterprise
import com.msg.sign_up.SignUpState.Government
import com.msg.sign_up.SignUpState.GradeAndNumber
import com.msg.sign_up.SignUpState.Job
import com.msg.sign_up.SignUpState.Loading
import com.msg.sign_up.SignUpState.Name
import com.msg.sign_up.SignUpState.Password
import com.msg.sign_up.SignUpState.PhoneNumber
import com.msg.sign_up.SignUpState.Position
import com.msg.sign_up.SignUpState.RePassword
import com.msg.sign_up.SignUpState.School
import com.msg.sign_up.SignUpState.Sectors
import com.msg.sign_up.component.SignUpBottomSheet
import com.msg.sign_up.data.BelongList
import com.msg.sign_up.data.HighSchoolList
import com.msg.sign_up.data.OutsideJobList
import com.msg.sign_up.data.SchoolJobList
import com.msg.sign_up.util.searchClubBySchool
import com.msg.sign_up.util.searchingInList
import com.msg.sign_up.viewmodel.SignUpViewModel

enum class SignUpState {
    Belong,
    Job,
    School,
    Club,
    Name,
    College,
    Enterprise,
    Government,
    Enrollment,
    GradeAndNumber,
    Position,
    Sectors,
    PhoneNumber,
    Email,
    Password,
    RePassword,
    Loading
}

enum class Keyboard {
    Opened, Closed
}

@Composable
private fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onBackClicked: () -> Unit,
    onEnterFinished: () -> Unit
) {
    SignUpScreen(
        onBackClicked = onBackClicked,
        onEnterFinished = { job: String, school: String, club: String, name: String, phoneNumber: String, college: String, enrollment: Int, enterprise: String, government: String, gradeAndNumber: String, email: String, password: String, position: String, sectors: String ->
            viewModel.job.value = job
            viewModel.school.value = school
            viewModel.club.value = club
            viewModel.name.value = name
            viewModel.phoneNumber.value = phoneNumber
            viewModel.college.value = college
            viewModel.enrollment.intValue = enrollment
            viewModel.enterprise.value = enterprise
            viewModel.government.value = government
            viewModel.gradeAndNumber.value = gradeAndNumber
            viewModel.position.value = position
            viewModel.sectors.value = sectors
            viewModel.email.value = email
            viewModel.password.value = password
            viewModel.signUp()
            onEnterFinished()
        }
    )
}

@Composable
internal fun SignUpScreen(
    onBackClicked: () -> Unit,
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
    focusManager: FocusManager = LocalFocusManager.current
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val signUpState = remember { mutableStateOf(Belong) }

    val showSignUpBottomSheet = remember { mutableStateOf(false) }
    val showJobTextField = remember { mutableStateOf(false) }
    val showSchoolTextField = remember { mutableStateOf(false) }
    val showClubTextField = remember { mutableStateOf(false) }
    val showNameTextField = remember { mutableStateOf(false) }
    val showCollegeTextField = remember { mutableStateOf(false) }
    val showEnterpriseTextField = remember { mutableStateOf(false) }
    val showGovernmentTextField = remember { mutableStateOf(false) }
    val showEnrollmentTextField = remember { mutableStateOf(false) }
    val showGradeAndNumberTextField = remember { mutableStateOf(false) }
    val showPositionTextField = remember { mutableStateOf(false) }
    val showSectorsTextField = remember { mutableStateOf(false) }
    val showPhoneNumberTextField = remember { mutableStateOf(false) }
    val showEmailTextField = remember { mutableStateOf(false) }
    val showPasswordTextField = remember { mutableStateOf(false) }
    val showRePasswordTextField = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }

    val isSelectedBelong = remember { mutableStateOf(false) }
    val isSelectedJob = remember { mutableStateOf(false) }
    val isSelectedSchool = remember { mutableStateOf(false) }
    val isSelectedClub = remember { mutableStateOf(false) }
    val isSelectedName = remember { mutableStateOf(false) }
    val isSelectedPhoneNumber = remember { mutableStateOf(false) }
    val isSelectedCollege = remember { mutableStateOf(false) }
    val isSelectedEnrollment = remember { mutableStateOf(false) }
    val isSelectedEnterprise = remember { mutableStateOf(false) }
    val isSelectedGovernment = remember { mutableStateOf(false) }
    val isSelectedGradeAndNumber = remember { mutableStateOf(false) }
    val isSelectedPosition = remember { mutableStateOf(false) }
    val isSelectedSectors = remember { mutableStateOf(false) }
    val isSelectedEmail = remember { mutableStateOf(false) }
    val isSelectedPassword = remember { mutableStateOf(false) }
    val isSelectedRePassword = remember { mutableStateOf(false) }
    val isActivatedBeforePhoneNumber = remember { mutableStateOf(false) }


    val belong = remember { mutableStateOf("") }
    val job = remember { mutableStateOf("") }
    val school = remember { mutableStateOf("") }
    val club = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val college = remember { mutableStateOf("") }
    val enrollment = remember { mutableIntStateOf(0) }
    val enterprise = remember { mutableStateOf("") }
    val government = remember { mutableStateOf("") }
    val gradeAndNumber = remember { mutableStateOf("") }
    val position = remember { mutableStateOf("") }
    val sectors = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val rePassword = remember { mutableStateOf("a") }

    var belongListForSearch = BelongList
    val outsideJobListForSearch = OutsideJobList
    val schoolJobListForSearch = SchoolJobList
    val schoolListForSearch = remember { mutableStateOf(HighSchoolList) }
    val clubListForSearch = remember { mutableStateOf(school.value.searchClubBySchool()) }

    val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,24}$")

    val isApplicationButtonEnabled = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            Column(
                modifier = Modifier
                    .background(colors.WHITE)
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                GoBackTopBar(
                    icon = { GoBackIcon() },
                    text = "돌아가기",
                    onClicked = { onBackClicked() }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    when (signUpState.value) {
                        Belong -> {
                            if (isSelectedBelong.value) {
                                showJobTextField.value = false
                                job.value = ""
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

                        Job -> {
                            if (isSelectedJob.value) {
                                showSchoolTextField.value = false
                                school.value = ""
                            }
                            showJobTextField.value = true
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

                        School -> {
                            if (isSelectedSchool.value) {
                                showClubTextField.value = false
                                club.value = ""
                            }
                            showSchoolTextField.value = true
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

                        Club -> {
                            if (isSelectedClub.value) {
                                showNameTextField.value = false
                                name.value = ""
                            }
                            showClubTextField.value = true
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

                        Name -> {
                            if (isSelectedName.value) {
                                showPhoneNumberTextField.value = false
                                showCollegeTextField.value = false
                                showEnrollmentTextField.value = false
                                showEnterpriseTextField.value = false
                                showGovernmentTextField.value = false
                                college.value = ""
                                enrollment.value = 0
                                enterprise.value = ""
                                government.value = ""
                            }
                            showNameTextField.value = true
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

                        College -> {
                            if (isSelectedCollege.value) {
                                showPhoneNumberTextField.value = false
                                phoneNumber.value = ""
                            }
                            showCollegeTextField.value = true
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

                        Enterprise -> {
                            if (isSelectedEnterprise.value) {
                                showPhoneNumberTextField.value = false
                                phoneNumber.value = ""
                            }
                            showEnterpriseTextField.value = true
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

                        Government -> {
                            if (isSelectedGovernment.value) {
                                showPositionTextField.value = false
                                sectors.value = ""
                            }
                            showGovernmentTextField.value = true
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

                        Enrollment -> {
                            Log.d("TAG", "hello")
                            if (isSelectedEnrollment.value) {
                                showGradeAndNumberTextField.value = false
                                gradeAndNumber.value = ""
                            }
                            showEnrollmentTextField.value = true
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

                        GradeAndNumber -> {
                            if (isSelectedGradeAndNumber.value) {
                                showPhoneNumberTextField.value = false
                                phoneNumber.value = ""
                            }
                            showGradeAndNumberTextField.value = true
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

                        Position -> {
                            if (isSelectedPosition.value) {
                                showSectorsTextField.value = false
                                position.value = ""
                            }
                            showPositionTextField.value = true
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

                        Sectors -> {
                            if (isSelectedSectors.value) {
                                showPhoneNumberTextField.value = false
                                phoneNumber.value = ""
                            }
                            showSectorsTextField.value = true
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

                        PhoneNumber -> {
                            if (isSelectedPhoneNumber.value) {
                                showEmailTextField.value = false
                                email.value = ""
                            }
                            showPhoneNumberTextField.value = true
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

                        Email -> {
                            if (isSelectedEmail.value) {
                                showPasswordTextField.value = false
                                password.value = ""
                            }
                            showEmailTextField.value = true
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

                        Password -> {
                            if (isSelectedPassword.value) {
                                showRePasswordTextField.value = false
                                rePassword.value = ""
                                isApplicationButtonEnabled.value = false
                            }
                            showPasswordTextField.value = true
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

                        RePassword -> {
                            showRePasswordTextField.value = true
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

                        Loading ->
                            if (password.value == rePassword.value) {
                                isApplicationButtonEnabled.value = true
                            }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier.verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (showRePasswordTextField.value && showPasswordTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedRePassword.value = true
                            PasswordTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value && (password.value == rePassword.value)) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "비밀번호",
                                errorText = "비밀번호가 일치하지 않습니다",
                                onValueChange = {
                                    rePassword.value = it
                                },
                                onLinkClicked = {},
                                isError = password.value == rePassword.value,
                                isLinked = false,
                                isDisabled = false,
                                onClicked = {
                                    if (signUpState.value != RePassword) signUpState.value =
                                        RePassword
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showPasswordTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedPassword.value = true
                            PasswordTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "비밀번호",
                                errorText = "비밀번호는 규칙에 맞게 입력해주세요",
                                onValueChange = {
                                    password.value = it
                                },
                                onLinkClicked = {},
                                isError = !passwordRegex.matches(password.value),
                                isLinked = false,
                                isDisabled = false,
                                onClicked = {
                                    if (signUpState.value != Password) signUpState.value = Password
                                    showRePasswordTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showEmailTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedEmail.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "이메일",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    email.value = it
                                },
                                onButtonClicked = { email.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != Email) signUpState.value = Email
                                    showPasswordTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showPhoneNumberTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedPhoneNumber.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "전화번호",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    phoneNumber.value = it
                                },
                                onButtonClicked = { phoneNumber.value = "" },
                                isReadOnly = false,
                                isNumberOnly = true,
                                onClicked = {
                                    if (signUpState.value != PhoneNumber) signUpState.value =
                                        PhoneNumber
                                    showEmailTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showGradeAndNumberTextField.value && showEnrollmentTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedGradeAndNumber.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "학번",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    gradeAndNumber.value = it
                                },
                                onButtonClicked = { gradeAndNumber.value = "" },
                                isReadOnly = false,
                                isNumberOnly = true,
                                onClicked = {
                                    if (signUpState.value != GradeAndNumber) signUpState.value =
                                        GradeAndNumber
                                    isActivatedBeforePhoneNumber.value = true
                                    showPhoneNumberTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showSectorsTextField.value && showPositionTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedSectors.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "직책 입력",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    position.value = it
                                },
                                onButtonClicked = { position.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != Sectors) signUpState.value =
                                        Sectors
                                    isActivatedBeforePhoneNumber.value = true
                                    showPhoneNumberTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showPositionTextField.value && showGovernmentTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedPosition.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "소속 기관의 업종",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    sectors.value = it
                                },
                                onButtonClicked = { sectors.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != Position) signUpState.value =
                                        Position
                                    showSectorsTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showGovernmentTextField.value && showNameTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedGovernment.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "소속 기관명",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    government.value = it
                                },
                                onButtonClicked = { government.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != Government) signUpState.value =
                                        Government
                                    isActivatedBeforePhoneNumber.value = true
                                    showPhoneNumberTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showCollegeTextField.value && showNameTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedCollege.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "소속 대학명",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    college.value = it
                                },
                                onButtonClicked = { college.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != College) signUpState.value = College
                                    isActivatedBeforePhoneNumber.value = true
                                    showPhoneNumberTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showEnterpriseTextField.value && showNameTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedEnterprise.value = false
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "소속 기업명",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    enterprise.value = it
                                },
                                onButtonClicked = { enterprise.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != Enterprise) signUpState.value =
                                        Enterprise
                                    isActivatedBeforePhoneNumber.value = true
                                    showPhoneNumberTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showEnrollmentTextField.value && showNameTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedEnrollment.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "입학년도",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    enrollment.value = it.toInt()
                                },
                                onButtonClicked = { enrollment.value = 0 },
                                isReadOnly = false,
                                isNumberOnly = true,
                                onClicked = {
                                    if (signUpState.value != Enrollment) signUpState.value =
                                        Enrollment
                                    showGradeAndNumberTextField.value = false
                                    isClicked.value = true
                                }
                            )
                        }
                        if (showNameTextField.value && showClubTextField.value) {
                            val isClicked = remember { mutableStateOf(false) }
                            if (keyboardAsState().value == Keyboard.Closed) {
                                focusManager.clearFocus()
                            }
                            isSelectedName.value = true
                            DefaultTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onFocusChanged {
                                        if (!it.isFocused && isClicked.value) signUpState.value =
                                            continueToNextField(signUpState.value, job.value)
                                    },
                                placeholder = "이름",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = false,
                                errorText = "",
                                onValueChange = {
                                    name.value = it
                                    if (it.length == 3) continueToNextField(
                                        signUpState.value,
                                        job.value
                                    )
                                },
                                onButtonClicked = { name.value = "" },
                                isReadOnly = false,
                                onClicked = {
                                    if (signUpState.value != Name) signUpState.value = Name
                                    if (job.value == "취업동아리 선생님") isActivatedBeforePhoneNumber.value =
                                        true
                                    isClicked.value = true
                                    showEnrollmentTextField.value = false
                                    showPhoneNumberTextField.value = false
                                    showEnterpriseTextField.value = false
                                    showCollegeTextField.value = false
                                    showGovernmentTextField.value = false
                                }
                            )
                        }
                        if (showClubTextField.value && showSchoolTextField.value) {
                            clubListForSearch.value = school.value.searchClubBySchool()
                            isSelectedClub.value = true
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = "동아리",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = true,
                                errorText = "",
                                onValueChange = {},
                                onButtonClicked = { club.value = "" },
                                isReadOnly = true,
                                onClicked = {
                                    if (signUpState.value != Club) signUpState.value = Club
                                    showSignUpBottomSheet.value = true
                                    showNameTextField.value = false
                                },
                                value = club.value
                            )
                        }
                        if (showSchoolTextField.value && showJobTextField.value) {
                            isSelectedSchool.value = true
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = "학교",
                                isError = false,
                                isLinked = false,
                                isDisabled = false,
                                isReverseTrailingIcon = true,
                                errorText = "",
                                onValueChange = {},
                                onButtonClicked = { school.value = "" },
                                isReadOnly = true,
                                onClicked = {
                                    if (signUpState.value != School) signUpState.value = School
                                    showSignUpBottomSheet.value = true
                                    showClubTextField.value = false
                                },
                                value = school.value
                            )
                        }
                        if (showJobTextField.value) {
                            isSelectedJob.value = true
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = "직업",
                                isError = false,
                                isLinked = false,
                                isDisabled = isLoading.value,
                                isReverseTrailingIcon = true,
                                errorText = "",
                                onValueChange = {},
                                onButtonClicked = { job.value = "" },
                                isReadOnly = true,
                                onClicked = {
                                    if (signUpState.value != Job) signUpState.value = Job
                                    showSignUpBottomSheet.value = true
                                    showSchoolTextField.value = false
                                },
                                value = job.value
                            )
                        }
                        DefaultTextField(
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "소속",
                            isError = false,
                            isLinked = false,
                            isDisabled = isLoading.value,
                            errorText = "",
                            onValueChange = {},
                            onButtonClicked = { belong.value = "" },
                            isReadOnly = true,
                            isReverseTrailingIcon = true,
                            onClicked = {
                                if (signUpState.value != Belong) signUpState.value = Belong
                                showSignUpBottomSheet.value = true
                            },
                            value = belong.value
                        )
                    }
                }
            }
            if (showSignUpBottomSheet.value) {
                when (signUpState.value) {
                    Belong -> {
                        SignUpBottomSheet(
                            list = belongListForSearch,
                            selectedItem = belong.value,
                            onSelectedItemChanged = {
                                if (belong.value != it) belong.value = it else belong.value = ""
                            },
                            onQuit = {
                                showSignUpBottomSheet.value = false
                                focusManager.clearFocus()
                                if (belong.value.isNotEmpty()) signUpState.value =
                                    continueToNextField(signUpState.value, belong.value)
                                isSelectedBelong.value = true
                                showJobTextField.value = true
                            },
                            isSearching = false,
                            onValueChanged = {
                                belongListForSearch = searchingInList(it, BelongList)
                            }
                        ) {

                        }
                    }

                    Job -> {
                        SignUpBottomSheet(
                            list = if (belong.value == "학교") schoolJobListForSearch else outsideJobListForSearch,
                            selectedItem = job.value,
                            isSearching = false,
                            onSelectedItemChanged = {
                                if (job.value != it) job.value = it else job.value = ""
                            },
                            onQuit = {
                                showSignUpBottomSheet.value = false
                                focusManager.clearFocus()
                                signUpState.value =
                                    continueToNextField(signUpState.value, job.value)
                            },
                            onValueChanged = {}
                        ) {

                        }
                    }

                    School -> {
                        SignUpBottomSheet(
                            list = schoolListForSearch.value,
                            selectedItem = school.value,
                            isSearching = true,
                            onSelectedItemChanged = {
                                if (school.value != it) school.value = it else school.value = ""
                            },
                            onQuit = {
                                showSignUpBottomSheet.value = false
                                focusManager.clearFocus()
                                signUpState.value =
                                    continueToNextField(signUpState.value, job.value)
                            },
                            onValueChanged = {
                                schoolListForSearch.value = searchingInList(it, HighSchoolList)
                            }
                        ) {

                        }
                    }

                    Club -> {
                        SignUpBottomSheet(
                            list = clubListForSearch.value,
                            selectedItem = club.value,
                            isSearching = true,
                            onSelectedItemChanged = {
                                if (club.value != it) club.value = it else club.value = ""
                            },
                            onQuit = {
                                showSignUpBottomSheet.value = false
                                focusManager.clearFocus()
                                signUpState.value =
                                    continueToNextField(signUpState.value, job.value)
                            },
                            onValueChanged = {
                                clubListForSearch.value =
                                    searchingInList(it, school.value.searchClubBySchool())
                            }
                        ) {

                        }
                    }

                    else -> {
                        showSignUpBottomSheet.value = false
                    }
                }
            }
            BitgoeulButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 28.dp, end = 28.dp, bottom = 16.dp)
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter),
                text = "회원가입 신청하기",
                state = if (isApplicationButtonEnabled.value) ButtonState.Enable else ButtonState.Disable,
                onClicked = {
                    onEnterFinished(
                        job.value,
                        school.value,
                        club.value,
                        name.value,
                        phoneNumber.value,
                        college.value,
                        enrollment.intValue,
                        enterprise.value,
                        government.value,
                        gradeAndNumber.value,
                        email.value,
                        password.value,
                        position.value,
                        sectors.value
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
        Belong -> return Job
        Job -> return School
        School -> return Club
        Club -> return Name
        Name -> {
            when (signUpInfo) {
                "학생" -> return Enrollment
                "취업동아리 선생님" -> return PhoneNumber
                "기업강사" -> return Enterprise
                "대학교수" -> return College
                "유관기관" -> return Government
                "뽀짝 선생님" -> return Government
            }
        }

        College -> return PhoneNumber
        Enterprise -> return PhoneNumber
        Government -> return Position
        Enrollment -> {
            Log.d("TAG", "here")
            return GradeAndNumber
        }
        GradeAndNumber -> return PhoneNumber
        Position -> return Sectors
        Sectors -> return PhoneNumber
        Password -> return RePassword
        RePassword -> return Loading
        Loading -> return Loading
        PhoneNumber -> return Email
        Email -> return Password
    }
    return Loading
}

@Preview
@Composable
fun SignUpScreenPre() {
    SignUpScreen(
        onBackClicked = {},
        onEnterFinished = { _, _, _, _, _, _, _, _, _, _, _, _, _, _-> }
    )
}