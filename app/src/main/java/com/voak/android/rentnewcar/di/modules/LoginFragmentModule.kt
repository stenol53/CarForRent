package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.LoginFragmentPresenter
import com.voak.android.rentnewcar.presenter.LoginFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.LoginFragmentRepository
import com.voak.android.rentnewcar.repository.LoginFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.LoginFragmentView
import dagger.Module
import dagger.Provides

@Module
class LoginFragmentModule(private val view: LoginFragmentView) {

    @Provides
    fun provideLoginFragmentPresenter(repository: LoginFragmentRepository): LoginFragmentPresenter {
        return LoginFragmentPresenterImpl(view, repository)
    }

    @Provides
    fun provideLoginFragmentRepository(apiService: RentNewCarApiService): LoginFragmentRepository {
        return LoginFragmentRepositoryImpl(apiService)
    }
}