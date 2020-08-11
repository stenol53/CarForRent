package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.di.modules.HistoryFragmentModule
import com.voak.android.rentnewcar.di.scopes.FragmentScope
import com.voak.android.rentnewcar.view.HistoryFragmentViewImpl
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [HistoryFragmentModule::class])
@FragmentScope
interface HistoryFragmentComponent {
    fun inject(view: HistoryFragmentViewImpl)
}