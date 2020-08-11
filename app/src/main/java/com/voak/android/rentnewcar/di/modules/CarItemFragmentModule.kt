package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.CarItemFragmentPresenter
import com.voak.android.rentnewcar.presenter.CarItemFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.CarItemFragmentRepository
import com.voak.android.rentnewcar.repository.CarItemFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.CarItemFragmentView
import dagger.Module
import dagger.Provides

@Module
class CarItemFragmentModule(val view: CarItemFragmentView) {

    @Provides
    fun provideCarItemFragmentRepository(apiService: RentNewCarApiService): CarItemFragmentRepository {
        return CarItemFragmentRepositoryImpl(apiService)
    }

    @Provides
    fun provideCarItemFragmentPresenter(repository: CarItemFragmentRepository): CarItemFragmentPresenter {
        return CarItemFragmentPresenterImpl(view, repository)
    }
}