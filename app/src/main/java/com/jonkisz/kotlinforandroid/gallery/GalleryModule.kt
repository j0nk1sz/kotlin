package com.jonkisz.kotlinforandroid.gallery

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val galleryModule = Kodein.Module {

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.102:8080")
                .build()
    }

    bind<GalleryApiService>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(GalleryApiService::class.java)
    }

}