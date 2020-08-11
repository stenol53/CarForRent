package com.voak.android.rentnewcar.view

import com.voak.android.rentnewcar.model.History

interface HistoryFragmentView {
    fun showRecyclerView()
    fun hideRecyclerView()
    fun showProgress()
    fun hideProgress()
    fun showToastMessage(message: String)
    fun setData(histories: List<History>)
}