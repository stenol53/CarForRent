package com.voak.android.rentnewcar.repository

interface EditPasswordFragmentRepository {
    fun updateClientPassword(
        clientId: Int,
        oldPassword: String,
        newPassword: String,
        resultOk: () -> Unit,
        resultError: (String) -> Unit
    )
}