package com.jonkisz.kotlinforandroid.gallery

import io.reactivex.Observable
import retrofit2.http.GET

interface GalleryApiService {

    @GET("/resource/info")
    fun info(): Observable<Model.Info>

    @GET("/resource/photos")
    fun photos(): Observable<List<Model.Photo>>

}


