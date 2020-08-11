package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.LoginFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.LoginFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [LoginFragmentModule::class])
@FragmentScope
interface LoginFragmentComponent {
    fun inject(loginFragment: LoginFragmentViewImpl)
}