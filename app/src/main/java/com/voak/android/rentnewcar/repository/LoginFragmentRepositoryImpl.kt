package com.voak.android.rentnewcar.repository

import android.util.Log
import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragmentRepositoryImpl(private val apiService: RentNewCarApiService) : LoginFragmentRepository {

    override fun makeAuth(
        login: String,
        password: String,
        resultOk: (UserData) -> Unit,
        resultError: (String) -> Unit
    ) {
        CoroutineScope(IO).launch {
            val result = apiService.login(login, password)

            withContext(Main) {
                if (result.isSuccessful) {
                    resultOk.invoke(result.body()!!)
                    Log.i("TAG", result.body()!!.toString())
                    } else {
                    resultError.invoke(result.errorBody()!!.string())
                }
            }
        }
    }
}