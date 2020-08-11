package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.model.UserData

interface EditInfoFragmentRepository {
    fun updateClientInfo(data:UserData, resultOk: () -> Unit, resultError: (String) -> Unit)
}