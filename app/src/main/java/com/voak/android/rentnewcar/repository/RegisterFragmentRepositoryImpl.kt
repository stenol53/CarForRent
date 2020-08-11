package com.voak.android.rentnewcar.repository


import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragmentRepositoryImpl(private val apiService: RentNewCarApiService)
    : RegisterFragmentRepository
{

    override fun makeRegister(data: UserData, resultOk: () -> Unit, resultError: (String) -> Unit) {
        CoroutineScope(IO).launch {
            val result = apiService.registerClient(
                data.login,
                data.password,
                data.firstName,
                data.secondName,
                data.middleName,
                data.address,
                data.phone
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