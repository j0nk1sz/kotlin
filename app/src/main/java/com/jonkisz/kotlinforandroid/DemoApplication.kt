package com.jonkisz.kotlinforandroid

import android.app.Application
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.jonkisz.kotlinforandroid.gallery.galleryModule
import org.jetbrains.anko.AnkoLogger


class DemoApplication : Application(), KodeinAware, AnkoLogger {

    override val kodein = Kodein {

        //import(autoAndroidModule(this@DemoApplication))
        import(galleryModule)

        // bind something elese here

    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(androidActivityScope.lifecycleManager)
    }
}