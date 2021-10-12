package com.daggoth.pruebaopenbank.data.api.utils

import com.daggoth.pruebaopenbank.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class ApiConectionUtils {

    companion object{

        const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()

        fun hash(): String {
            val valueText = "$ts${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(valueText.toByteArray())).toString(16).padStart(32, '0')
        }

    }
}