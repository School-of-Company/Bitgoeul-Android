package com.msg.sign_up.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.common.errorhandling.errorHandling
import com.msg.common.event.Event
import com.msg.domain.usecase.auth.SignUpBbozzakTeacherUseCase
import com.msg.domain.usecase.auth.SignUpCompanyInstructorUseCase
import com.msg.domain.usecase.auth.SignUpGovernmentUseCase
import com.msg.domain.usecase.auth.SignUpJobClubTeacherUseCase
import com.msg.domain.usecase.auth.SignUpProfessorUseCase
import com.msg.domain.usecase.auth.SignUpStudentUseCase
import com.msg.model.param.auth.SignUpBbozzakTeacherParam
import com.msg.model.param.auth.SignUpCompanyInstructorParam
import com.msg.model.param.auth.SignUpGovernmentParam
import com.msg.model.param.auth.SignUpJobClubTeacherParam
import com.msg.model.param.auth.SignUpProfessorParam
import com.msg.model.param.auth.SignUpStudentParam
import com.msg.sign_up.util.searchEngSchoolNameByKrSchoolName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpStudentUseCase: SignUpStudentUseCase,
    private val signUpTeacherUseCase: SignUpJobClubTeacherUseCase,
    private val signUpBbozzakTeacherUseCase: SignUpBbozzakTeacherUseCase,
    private val signUpProfessorUseCase: SignUpProfessorUseCase,
    private val signUpGovernmentUseCase: SignUpGovernmentUseCase,
    private val signUpCompanyInstructorUseCase: SignUpCompanyInstructorUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val JOB = "job"
        private const val SCHOOL = "school"
        private const val CLUB = "club"
        private const val NAME = "name"
        private const val PHONE_NUMBER = "phoneNumber"
        private const val COLLEGE = "college"
        private const val ENROLLMENT = "enrollment"
        private const val ENTERPRISE = "enterprise"
        private const val GOVERNMENT = "government"
        private const val GRADE_AND_NUMBER = "gradeAndNumber"
        private const val POSITION = "position"
        private const val SECTORS = "sectors"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val BELONG = "belong"
        private const val REPASSWORD = "rePassword"
    }

    private val _signUpResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val signUpResponse = _signUpResponse.asStateFlow()

    internal var belong = savedStateHandle.getStateFlow(key = BELONG, initialValue = "")

    internal var job = savedStateHandle.getStateFlow(key = JOB, initialValue = "")

    internal var school = savedStateHandle.getStateFlow(key = SCHOOL, initialValue = "")

    internal var club = savedStateHandle.getStateFlow(key = CLUB, initialValue = "")

    internal var name = savedStateHandle.getStateFlow(key = NAME, initialValue = "")

    internal var phoneNumber = savedStateHandle.getStateFlow(key = PHONE_NUMBER, initialValue = "")

    internal var college = savedStateHandle.getStateFlow(key = COLLEGE, initialValue = "")

    internal var enrollment = savedStateHandle.getStateFlow(key = ENROLLMENT, initialValue = 0)

    internal var enterprise = savedStateHandle.getStateFlow(key = ENTERPRISE, initialValue = "")

    internal var government = savedStateHandle.getStateFlow(key = GOVERNMENT, initialValue = "")

    internal var gradeAndNumber = savedStateHandle.getStateFlow(key = GRADE_AND_NUMBER, initialValue = "")

    internal var position = savedStateHandle.getStateFlow(key = POSITION, initialValue = "")

    internal var sectors = savedStateHandle.getStateFlow(key = SECTORS, initialValue = "")

    internal var email = savedStateHandle.getStateFlow(key = EMAIL, initialValue = "")

    internal var password = savedStateHandle.getStateFlow(key = PASSWORD, initialValue = "")

    internal var rePassword = savedStateHandle.getStateFlow(key = REPASSWORD, initialValue = "a")

    fun signUp() = viewModelScope.launch {
        when (job.value) {
            "학생" -> {
                signUpStudentUseCase(
                    body = SignUpStudentParam(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value,
                        grade = gradeAndNumber.value.substring(0, 1).toInt(),
                        classRoom = gradeAndNumber.value.substring(1, 2).toInt(),
                        number = gradeAndNumber.value.substring(2).toInt(),
                        admissionNumber = enrollment.value
                    )
                ).onSuccess {
                    it.catch { remoteError ->
                        _signUpResponse.value = remoteError.errorHandling()
                    }.collect {
                        _signUpResponse.value = Event.Success()
                    }
                }.onFailure { error ->
                    _signUpResponse.value = error.errorHandling()
                }
            }
            "취업동아리 선생님" -> {
                signUpTeacherUseCase(
                    body = SignUpJobClubTeacherParam(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value
                    )
                ).onSuccess {
                    it.catch { remoteError ->
                        _signUpResponse.value = remoteError.errorHandling()
                    }.collect {
                        _signUpResponse.value = Event.Success()
                    }
                }.onFailure { error ->
                    _signUpResponse.value = error.errorHandling()
                }
            }
            "기업강사" -> {
                signUpCompanyInstructorUseCase(
                    body = SignUpCompanyInstructorParam(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value,
                        company = enterprise.value
                    )
                ).onSuccess {
                    it.catch { remoteError ->
                        _signUpResponse.value = remoteError.errorHandling()
                    }.collect {
                        _signUpResponse.value = Event.Success()
                    }
                }.onFailure { error ->
                    _signUpResponse.value = error.errorHandling()
                }
            }
            "대학교수" -> {
                signUpProfessorUseCase(
                    body = SignUpProfessorParam(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value,
                        university = college.value
                    )
                ).onSuccess {
                    it.catch { remoteError ->
                        _signUpResponse.value = remoteError.errorHandling()
                    }.collect {
                        _signUpResponse.value = Event.Success()
                    }
                }.onFailure { error ->
                    _signUpResponse.value = error.errorHandling()
                }
            }
            "유관기관" -> {
                signUpGovernmentUseCase(
                    body = SignUpGovernmentParam(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value,
                        governmentName = government.value,
                        position = position.value,
                        sectors = sectors.value,
                    )
                ).onSuccess {
                    it.catch { remoteError ->
                        _signUpResponse.value = remoteError.errorHandling()
                    }.collect {
                        _signUpResponse.value = Event.Success()
                    }
                }.onFailure { error ->
                    _signUpResponse.value = error.errorHandling()
                }
            }
            "뽀짝 선생님" -> {
                signUpBbozzakTeacherUseCase(
                    body = SignUpBbozzakTeacherParam(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value
                    )
                ).onSuccess {
                    it.catch { remoteError ->
                        _signUpResponse.value = remoteError.errorHandling()
                    }.collect {
                        _signUpResponse.value = Event.Success()
                    }
                }.onFailure { error ->
                    _signUpResponse.value = error.errorHandling()
                }
            }
        }
    }

    internal fun onJobChange(value: String) { savedStateHandle[JOB] = value }

    internal fun onSchoolChange(value: String) { savedStateHandle[SCHOOL] = value }

    internal fun onClubChange(value: String) { savedStateHandle[CLUB] = value }

    internal fun onNameChange(value: String) { savedStateHandle[NAME] = value }

    internal fun onPhoneNumberChange(value: String) { savedStateHandle[PHONE_NUMBER] = value }

    internal fun onCollegeChange(value: String) { savedStateHandle[COLLEGE] = value }

    internal fun onEnrollmentChange(value: Int) { savedStateHandle[ENROLLMENT] = value }

    internal fun onEnterpriseChange(value: String) { savedStateHandle[ENTERPRISE] = value }

    internal fun onGovernmentChange(value: String) { savedStateHandle[GOVERNMENT] = value }

    internal fun onGradeAndNumberChange(value: String) { savedStateHandle[GRADE_AND_NUMBER] = value }

    internal fun onPositionChange(value: String) { savedStateHandle[POSITION] = value }

    internal fun onSectorsChange(value: String) { savedStateHandle[SECTORS] = value }

    internal fun onEmailChange(value: String) { savedStateHandle[EMAIL] = value }

    internal fun onPasswordChange(value: String) { savedStateHandle[PASSWORD] = value }

    internal fun onBelongChange(value: String) { savedStateHandle[BELONG] = value }

    internal fun onRePasswordChange(value: String) { savedStateHandle[REPASSWORD] = value }
}