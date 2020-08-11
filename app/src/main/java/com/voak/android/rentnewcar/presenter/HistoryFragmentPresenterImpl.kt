package com.voak.android.rentnewcar.presenter

import android.util.Log
import com.voak.android.rentnewcar.repository.HistoryFragmentRepository
import com.voak.android.rentnewcar.utils.QueryPreferences
import com.voak.android.rentnewcar.view.HistoryFragmentView

class HistoryFragmentPresenterImpl(
    val view: HistoryFragmentView,
    val repository: HistoryFragmentRepository
) : HistoryFragmentPresenter {
    override fun onCreateView() {
        view.hideRecyclerView()
        view.showProgress()
        repository.getHistories(
            QueryPreferences.getClientId(),
            {
                view.setData(it.reversed())
                view.hideProgress()
                view.showRecyclerView()
            },
            {
                view.hideProgress()
                view.showToastMessage(it)
            }
        )
    }
}