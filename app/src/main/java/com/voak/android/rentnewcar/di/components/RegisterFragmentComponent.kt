package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.RegisterFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.RegisterFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [RegisterFragmentModule::class])
@FragmentScope
interface RegisterFragmentComponent {
    fun inject(registerFragment: RegisterFragmentViewImpl)
}