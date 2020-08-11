package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.model.History

interface HistoryFragmentRepository {
    fun getHistories(clientId: Int, resultOk: (List<History>) -> Unit, resultError: (String) -> Unit)
}