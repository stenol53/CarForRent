package com.voak.android.rentnewcar.utils

import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.voak.android.rentnewcar.MyApplication
import java.util.concurrent.Executors

object PicassoObj {

    private lateinit var picasso: Picasso

    init {
        val downloader = OkHttp3Downloader(UnsafeOkHttpClient.getUnsafeOkHttpClient())
        picasso = Picasso.Builder(MyApplication.instance.applicationContext)
            .downloader(downloader)
            .memoryCache(LruCache(1048576))
            .executor(Executors.newSingleThreadExecutor())
//            .indicatorsEnabled(true)
            .build()
    }

    internal fun getPicasso(): Picasso = picasso
}