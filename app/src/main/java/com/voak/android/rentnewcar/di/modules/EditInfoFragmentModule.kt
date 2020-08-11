package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.presenter.EditInfoFragmentPresenter
import com.voak.android.rentnewcar.presenter.EditInfoFragmentPresenterImpl
import com.voak.android.rentnewcar.repository.EditInfoFragmentRepository
import com.voak.android.rentnewcar.repository.EditInfoFragmentRepositoryImpl
import com.voak.android.rentnewcar.view.EditInfoFragmentView
import dagger.Module
import dagger.Provides

@Module
class EditInfoFragmentModule(val view: EditInfoFragmentView) {

    @Provides
    fun provideEditInfoFragmentRepository(apiService: RentNewCarApiService): EditInfoFragmentRepository {
        return EditInfoFragmentRepositoryImpl(apiService)
    }

    @Provides
    fun provideEditInfoFragmentPresenter(repository: EditInfoFragmentRepository): EditInfoFragmentPresenter {
        return EditInfoFragmentPresenterImpl(view, repository)
    }

}