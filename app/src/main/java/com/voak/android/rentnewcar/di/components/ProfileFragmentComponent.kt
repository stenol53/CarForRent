package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.ProfileFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.ProfileFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [ProfileFragmentModule::class])
@FragmentScope
interface ProfileFragmentComponent {
    fun inject(view: ProfileFragmentViewImpl)
}