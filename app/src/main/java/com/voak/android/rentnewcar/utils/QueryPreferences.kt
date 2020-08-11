package com.voak.android.rentnewcar.utils

import androidx.preference.PreferenceManager
import com.voak.android.rentnewcar.MyApplication

object QueryPreferences {
    private const val PREF_CLIENT_LOGIN: String = "clientLogin"
    private const val PREF_CLIENT_PASSWORD: String = "clientPassword"
    private const val PREF_CLIENT_ID: String = "clientId"

    private val context = MyApplication.instance.applicationContext

    internal fun setClientAuthData(login: String?, password: String?, id: Int) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(PREF_CLIENT_LOGIN, login)
            .putString(PREF_CLIENT_PASSWORD, password)
            .putInt(PREF_CLIENT_ID, id)
            .apply()
    }

    internal fun getClientLogin(): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_CLIENT_LOGIN, null)
    }

    internal fun getClientPassword(): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_CLIENT_PASSWORD, null)
    }

    internal fun getClientId(): Int {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getInt(PREF_CLIENT_ID, -1)
    }

}