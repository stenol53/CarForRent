package com.voak.android.rentnewcar.repository

import android.util.Log
import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarsFragmentRepositoryImpl(val apiService: RentNewCarApiService) : CarsFragmentRepository {

    override fun getCars(resultOk: (List<Car>) -> Unit, resultError: (String) -> Unit) {
        Log.i("REPOSITORY", "1")
        CoroutineScope(IO).launch {
            Log.i("REPOSITORY", "2")
            val result = apiService.getCars("active")
            Log.i("REPOSITORY", "3")
            withContext(Main) {
                if (result.isSuccessful) {
                    Log.i("REPOSITORY", result.body().toString())
                    resultOk.invoke(result.body()!!)
                } else {
                    Log.i("REPOSITORY", result.errorBody()!!.string())
                    resultError.invoke(result.errorBody()!!.string())
                }
            }
        }
    }
}