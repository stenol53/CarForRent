package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.CarItemFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.CarItemFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [CarItemFragmentModule::class])
@FragmentScope
interface CarItemFragmentComponent {
    fun inject(view: CarItemFragmentViewImpl)
}