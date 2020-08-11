package com.voak.android.rentnewcar.di.modules

import com.voak.android.rentnewcar.utils.UnsafeOkHttpClient
import com.voak.android.rentnewcar.api.RentNewCarApiService
import com.voak.android.rentnewcar.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    fun provideRentNewCarApi(retrofit: Retrofit): RentNewCarApiService {
        return retrofit.create(RentNewCarApiService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}