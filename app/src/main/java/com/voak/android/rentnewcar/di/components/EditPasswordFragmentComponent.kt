package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.EditPasswordFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.EditPasswordFragmentViewIml
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [EditPasswordFragmentModule::class])
@FragmentScope
interface EditPasswordFragmentComponent {

    fun inject(view: EditPasswordFragmentViewIml)
}