package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.CarsFragmentPresenter
import com.voak.android.rentnewcar.presenter.CarsFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.CarsFragmentRepository
import com.voak.android.rentnewcar.repository.CarsFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.CarsFragmentView
import dagger.Module
import dagger.Provides

@Module
class CarsFragmentModule(val view: CarsFragmentView) {

    @Provides
    fun provideCarsFragmentRepository(apiService: RentNewCarApiService): CarsFragmentRepository {
        return CarsFragmentRepositoryImpl(apiService)
    }

    @Provides
    fun provideCarsFragmentPresenter(repository: CarsFragmentRepository): CarsFragmentPresenter {
        return CarsFragmentPresenterImpl(view, repository)
    }
}