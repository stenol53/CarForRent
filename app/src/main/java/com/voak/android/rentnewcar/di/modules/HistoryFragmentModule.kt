package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.HistoryFragmentPresenter
import com.voak.android.rentnewcar.presenter.HistoryFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.HistoryFragmentRepository
import com.voak.android.rentnewcar.repository.HistoryFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.HistoryFragmentView
import dagger.Module
import dagger.Provides

@Module
class HistoryFragmentModule(val view: HistoryFragmentView) {

    @Provides
    fun provideHistoryFragmentRepository(apiService: RentNewCarApiService) : HistoryFragmentRepository {
        return HistoryFragmentRepositoryImpl(apiService)
    }

    @Provides
    fun provideHistoryFragmentPresenter(repository: HistoryFragmentRepository) : HistoryFragmentPresenter {
        return HistoryFragmentPresenterImpl(view, repository)
    }

}