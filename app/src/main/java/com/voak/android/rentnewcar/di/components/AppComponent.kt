package com.voak.android.rentnewcar.di.components

import android.content.Context
import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.di.modules.AppModule
import com.voak.android.rentnewcar.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun provideContext(): Context
    fun provideRentNewCarApi(): RentNewCarApiService
}