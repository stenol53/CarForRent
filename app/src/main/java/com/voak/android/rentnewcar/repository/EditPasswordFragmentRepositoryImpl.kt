package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.api.RentNewCarApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditPasswordFragmentRepositoryImpl(val apiService: RentNewCarApiService) : EditPasswordFragmentRepository {

    override fun updateClientPassword(
        clientId: Int,
        oldPassword: String,
        newPassword: String,
        resultOk: () -> Unit,
        resultError: (String) -> Unit
    ) {
        CoroutineScope(IO).launch {
            val result = apiService.updateClientPassword(clientId, oldPassword, newPassword)

            withContext(Main) {
                if (result.isSuccessful) {
                    resultOk.invoke()
                } else {
                    resultError.invoke(result.errorBody()!!.string())
                }
            }
        }
    }
}