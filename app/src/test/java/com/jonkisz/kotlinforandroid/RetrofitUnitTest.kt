package com.jonkisz.kotlinforandroid


import com.jonkisz.kotlinforandroid.gallery.GalleryApiService
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUnitTest : AnkoLogger {

    @Test
    fun flickrRecentImages() {

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://localhost:8080")
                .build()

        val resourceService = retrofit.create(GalleryApiService::class.java)

        val disposable = resourceService.photos()
                //.subscribeOn(Schedulers.io()) // fetch data on background
                //.observeOn(AndroidSchedulers.mainThread()) // fetach data in UI thread
                .subscribeOn(Schedulers.single()) // fetch data on background
                .observeOn(Schedulers.single()) // fetach data in UI thread
                .subscribe(
                        { result ->
                            result.forEach { photo ->
                                println("title: ${photo.title} location: ${photo.location}")
                            }
                        },
                        { err -> print("cannot fetch data: ${err.message}") }
                )

        TimeUnit.SECONDS.sleep(1)
        disposable.dispose()

        // onPause() { super.onPause(); disposable?.dispose() }

    }
}


