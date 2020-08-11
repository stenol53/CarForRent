package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarItemFragmentRepositoryImpl(val apiService: RentNewCarApiService) : CarItemFragmentRepository {

    override fun getCar(carId: Int, resultOk: (Car) -> Unit, resultError: (String) -> Unit) {
        CoroutineScope(IO).launch {
            val result = apiService.getCarById(carId)

            withContext(Main) {
                if (result.isSuccessful) {
                    resultOk.invoke(result.body()!!)
                } else {
                    resultError.invoke(result.errorBody()!!.string())
                }
            }
        }
    }

    override fun makeOrder(
        clientId: Int,
        carId: Int,
        startDate: Long,
        endDate: Long,
        resultOk: () -> Unit,
        resultError: (String) -> Unit
    ) {
        CoroutineScope(IO).launch {
            val result = apiService.makeOrder(
                clientId,
                carId,
                startDate,
                endDate,
                "active"
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