package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.EditInfoFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.EditInfoFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [EditInfoFragmentModule::class])
@FragmentScope
interface EditInfoFragmentComponent {
    fun inject(view: EditInfoFragmentViewImpl)
}