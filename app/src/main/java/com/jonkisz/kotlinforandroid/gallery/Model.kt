package com.jonkisz.kotlinforandroid.gallery

object Model {

    data class Photo(val title: String, val location: String)
    data class Info(val name: String, val size: Int)

}