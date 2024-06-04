package com.msg.sign_up.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.auth.SignUpBbozzakTeacherUseCase
import com.msg.domain.auth.SignUpCompanyInstructorUseCase
import com.msg.domain.auth.SignUpGovernmentUseCase
import com.msg.domain.auth.SignUpJobClubTeacherUseCase
import com.msg.domain.auth.SignUpProfessorUseCase
import com.msg.domain.auth.SignUpStudentUseCase
import com.msg.model.remote.request.auth.SignUpBbozzakTeacherRequest
import com.msg.model.remote.request.auth.SignUpCompanyInstructorRequest
import com.msg.model.remote.request.auth.SignUpGovernmentRequest
import com.msg.model.remote.request.auth.SignUpJobClubTeacherRequest
import com.msg.model.remote.request.auth.SignUpProfessorRequest
import com.msg.model.remote.request.auth.SignUpStudentRequest
import com.msg.sign_up.util.Event
import com.msg.sign_up.util.errorHandling
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
    private val signUpCompanyInstructorUseCase: SignUpCompanyInstructorUseCase
) : ViewModel() {

    private val _signUpResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val signUpResponse = _signUpResponse.asStateFlow()

    var job = mutableStateOf("")
        private set
    var school = mutableStateOf("")
        private set
    var club = mutableStateOf("")
        private set
    var name = mutableStateOf("")
        private set
    var phoneNumber = mutableStateOf("")
        private set
    var college = mutableStateOf("")
        private set
    var enrollment = mutableIntStateOf(0)
        private set
    var enterprise = mutableStateOf("")
        private set
    var government = mutableStateOf("")
        private set
    var gradeAndNumber = mutableStateOf("")
        private set
    var position = mutableStateOf("")
        private set
    var sectors = mutableStateOf("")
        private set
    var email = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set

    fun signUp() = viewModelScope.launch {
        when (job.value) {
            "학생" -> {
                signUpStudentUseCase(
                    body = SignUpStudentRequest(
                        email = email.value,
                        name = name.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value,
                        highSchool = school.value.searchEngSchoolNameByKrSchoolName(),
                        clubName = club.value,
                        grade = gradeAndNumber.value.substring(0, 1).toInt(),
                        classRoom = gradeAndNumber.value.substring(1, 2).toInt(),
                        number = gradeAndNumber.value.substring(2).toInt(),
                        admissionNumber = enrollment.intValue
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
                    body = SignUpJobClubTeacherRequest(
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
                    body = SignUpCompanyInstructorRequest(
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
                    body = SignUpProfessorRequest(
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
                    body = SignUpGovernmentRequest(
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
                    body = SignUpBbozzakTeacherRequest(
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

}