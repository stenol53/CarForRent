package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.view.LoginFragmentViewImpl
import com.voak.android.rentnewcar.view.LoginFragmentViewImpl.OnCreateNewAccountListener
import com.voak.android.rentnewcar.view.RegisterFragmentViewImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideLoginFragment(): LoginFragmentViewImpl {
        return LoginFragmentViewImpl()
    }

    @Provides
    fun provideRegisterFragment(): RegisterFragmentViewImpl {
        return RegisterFragmentViewImpl()
    }
}