package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.EditPasswordFragmentPresenter
import com.voak.android.rentnewcar.presenter.EditPasswordFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.EditPasswordFragmentRepository
import com.voak.android.rentnewcar.repository.EditPasswordFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.EditPasswordFragmentView
import dagger.Module
import dagger.Provides

@Module
class EditPasswordFragmentModule(val view: EditPasswordFragmentView) {

    @Provides
    fun provideEditPasswordFragmentRepository(apiService: RentNewCarApiService): EditPasswordFragmentRepository {
        return EditPasswordFragmentRepositoryImpl(apiService)
    }

    @Provides
    fun provideEditPasswordFragmentPresenter(repository: EditPasswordFragmentRepository): EditPasswordFragmentPresenter {
        return EditPasswordFragmentPresenterImpl(view, repository)
    }

}