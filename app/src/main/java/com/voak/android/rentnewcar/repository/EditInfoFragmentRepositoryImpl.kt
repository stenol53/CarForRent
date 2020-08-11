package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.UserData
import com.voak.android.rentnewcar.utils.QueryPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditInfoFragmentRepositoryImpl(val apiService: RentNewCarApiService) : EditInfoFragmentRepository {

    override fun updateClientInfo(
        data: UserData,
        resultOk: () -> Unit,
        resultError: (String) -> Unit
    ) {
        CoroutineScope(IO).launch {
            val result = apiService.updateClientInfo(
                data.id,
                if (data.login == QueryPreferences.getClientLogin()) null else data.login,
                data.firstName,
                data.secondName,
                data.middleName,
                data.phone,
                data.address
            )

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