package com.jonkisz.kotlinforandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.jonkisz.kotlinforandroid.gallery.GalleryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.*

class GalleryActivity : AppCompatActivity(), KodeinInjected, AnkoLogger {
    override val injector = KodeinInjector()

    val galleryService: GalleryApiService by instance()
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(appKodein())

        val progress = indeterminateProgressDialog("Please wait for pictures")
        progress.show()

        relativeLayout {
            listView {

            }
        }

        disposable = galleryService.photos()
                .subscribeOn(Schedulers.io()) // fetch data on background
                .observeOn(AndroidSchedulers.mainThread()) // fetach data in UI thread
                .subscribe(
                        { result ->
                            progress.dismiss()
                            result.forEach { photo ->
                                info {
                                    "title: ${photo.title} location: ${photo.location}"
                                }
                            }
                        },
                        { err ->
                            progress.dismiss()
                            error { "cannot fetch data: ${err.message}" }
                        }
                )



    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
