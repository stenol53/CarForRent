package com.voak.android.rentnewcar.di.components

import com.voak.android.rentnewcar.MainActivity
import com.voak.android.rentnewcar.di.modules.MainActivityModule
import com.voak.android.rentnewcar.di.scopes.ActivityScope
import dagger.Component

@Component(modules = [MainActivityModule::class])
@ActivityScope
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}