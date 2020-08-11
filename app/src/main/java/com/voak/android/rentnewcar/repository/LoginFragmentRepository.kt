package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.model.UserData

interface LoginFragmentRepository {
    fun makeAuth(
        login: String,
        password: String,
        resultOk: (UserData) -> Unit,
        resultError: (String) -> Unit
    )


}