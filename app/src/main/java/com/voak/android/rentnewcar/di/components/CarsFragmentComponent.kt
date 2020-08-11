package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.CarsFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.CarsFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [CarsFragmentModule::class])
@FragmentScope
interface CarsFragmentComponent {
    fun inject(view: CarsFragmentViewImpl)
}