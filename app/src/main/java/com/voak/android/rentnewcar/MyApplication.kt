package com.voak.android.rentnewcar

import android.app.Application
import com.voak.android.rentnewcar.di.components.AppComponent
import com.voak.android.rentnewcar.di.components.DaggerAppComponent
import com.voak.android.rentnewcar.di.modules.AppModule

class MyApplication : Application() {

    lateinit var component: AppComponent

    companion object {
        lateinit var instance: MyApplication private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    private fun setup() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent = component

}