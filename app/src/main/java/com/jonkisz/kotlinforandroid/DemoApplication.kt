package com.jonkisz.kotlinforandroid

import android.app.Application
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope
import messages.meassagingModule
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class DemoApplication : Application(), KodeinAware, AnkoLogger {

    override val kodein = Kodein {

        //import(autoAndroidModule(this@DemoApplication))

        import(meassagingModule)

        // bind something elese here

        info { "configured dependencies"}

    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(androidActivityScope.lifecycleManager)
        info { "Creating application"}

    }
}