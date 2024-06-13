package com.msg.model.enumdata

enum class Authority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_BBOZZAK,
    ROLE_PROFESSOR,
    ROLE_COMPANY_INSTRUCTOR,
    ROLE_GOVERNMENT;

    companion object {
        fun from(role: String): Authority {
            return when (role) {
                "ROLE_USER" -> ROLE_USER
                "ROLE_ADMIN" -> ROLE_ADMIN
                "ROLE_STUDENT" -> ROLE_STUDENT
                "ROLE_TEACHER" -> ROLE_TEACHER
                "ROLE_BBOZZAK" -> ROLE_BBOZZAK
                "ROLE_PROFESSOR" -> ROLE_PROFESSOR
                "ROLE_COMPANY_INSTRUCTOR" -> ROLE_COMPANY_INSTRUCTOR
                "ROLE_GOVERNMENT" -> ROLE_GOVERNMENT
                else -> throw IllegalArgumentException("Unknown role: $role")
            }
        }
    }
}