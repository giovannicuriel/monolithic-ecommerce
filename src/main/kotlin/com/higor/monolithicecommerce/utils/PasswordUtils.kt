package com.higor.monolithicecommerce.utils

class PasswordUtils {

    companion object{
        fun encrypt(password: String): String = password.reversed()
    }
}