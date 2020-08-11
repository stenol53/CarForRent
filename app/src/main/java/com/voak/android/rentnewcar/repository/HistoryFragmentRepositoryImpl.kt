package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.model.History
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragmentRepositoryImpl(val apiService: RentNewCarApiService) : HistoryFragmentRepository {

    override fun getHistories(
        clientId: Int,
        resultOk: (List<History>) -> Unit,
        resultError: (String) -> Unit
    ) {
        CoroutineScope(IO).launch {
            val result = apiService.getHistories(clientId)

            withContext(Main) {
                if (result.isSuccessful) {
                    resultOk.invoke(result.body()!!)
                } else {
                    resultError.invoke(result.errorBody()!!.string())
                }
            }
        }
    }
}