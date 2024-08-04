package com.cm.schoolcontentmanager.utils

object Util {

    private const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

    fun isValidPassword(password: String): Boolean {
        return password.matches(PASSWORD_REGEX.toRegex())
    }

}