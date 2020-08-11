package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.model.UserData

interface ProfileFragmentRepository {

    fun getClientDataByLogin(
        login:String,
        resultOk: (UserData) -> Unit,
        resultError: (String) -> Unit
    )

}