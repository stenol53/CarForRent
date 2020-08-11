package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.model.UserData


interface RegisterFragmentRepository {
    fun makeRegister(data: UserData, resultOk: () -> Unit, resultError: (String) -> Unit)
}