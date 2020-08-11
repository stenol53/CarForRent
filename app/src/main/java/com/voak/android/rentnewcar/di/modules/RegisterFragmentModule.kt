package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.RegisterFragmentPresenter
import com.voak.android.rentnewcar.presenter.RegisterFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.RegisterFragmentRepository
import com.voak.android.rentnewcar.repository.RegisterFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.RegisterFragmentView
import dagger.Module
import dagger.Provides

@Module
class RegisterFragmentModule(private val view: RegisterFragmentView) {

    @Provides
    fun provideRegisterFragmentPresenter(repository: RegisterFragmentRepository): RegisterFragmentPresenter {
        return RegisterFragmentPresenterImpl(view, repository)
    }

    @Provides
    fun provideRegisterFragmentRepository(apiService: RentNewCarApiService): RegisterFragmentRepository {
        return RegisterFragmentRepositoryImpl(apiService)
    }

}