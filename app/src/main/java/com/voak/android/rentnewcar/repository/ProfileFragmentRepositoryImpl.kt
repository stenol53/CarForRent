package com.voak.android.rentnewcar.repository

import android.util.Log
import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentRepositoryImpl(val apiService: RentNewCarApiService) : ProfileFragmentRepository {

    override fun getClientDataByLogin(
        login: String,
        resultOk: (UserData) -> Unit,
        resultError: (String) -> Unit
    ) {
        CoroutineScope(IO).launch {
            val result = apiService.getUser(login)

            withContext(Main) {
                if (result.isSuccessful) {
                    Log.i("ProfileRepo", result.body()!!.toString())
                    resultOk.invoke(result.body()!!)
                } else {
                    resultError.invoke(result.errorBody()!!.string())
                }
            }
        }
    }
}