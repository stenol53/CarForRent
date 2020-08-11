package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.ProfileFragmentPresenter
import com.voak.android.rentnewcar.presenter.ProfileFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.ProfileFragmentRepository
import com.voak.android.rentnewcar.repository.ProfileFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.ProfileFragmentView
import dagger.Module
import dagger.Provides

@Module
class ProfileFragmentModule(val view: ProfileFragmentView) {

    @Provides
    fun provideProfileFragmentRepository(apiService: RentNewCarApiService): ProfileFragmentRepository {
        return ProfileFragmentRepositoryImpl(apiService)
    }

    @Provides
    fun provideProfileFragmentPresenter(repository: ProfileFragmentRepository): ProfileFragmentPresenter {
        return ProfileFragmentPresenterImpl(view, repository)
    }
}