package com.jonkisz.kotlinforandroid

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.net.URI

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var messageText = "Blank message"

        if (intent.hasExtra("com.jonkisz.kotlinforandroid.MESSAGE")) {
            messageText = intent.getStringExtra("com.jonkisz.kotlinforandroid.MESSAGE")
        } else if (intent.hasExtra(Intent.EXTRA_TEXT)){
            messageText = intent.getStringExtra(Intent.EXTRA_TEXT)
        }

        relativeLayout {
            val messageText = textView {
                id = 1
                hint = messageText
            }.lparams {
                alignParentLeft()
                alignParentRight()
                alignParentTop()
            }


            button("Show map") {
                id = 2
                onClick {
                    val geoURI = "geo:37.422,-122.084?z=16"

                    browse(geoURI)
                }
            }.lparams {
                below(1)
                alignParentLeft()
                alignParentRight()
            }


            button("Show page") {
                id = 3
                onClick {
                    val webURI = "http://www.google.com"
                    browse(webURI)
                }
            }.lparams {
                below(2)
                alignParentLeft()
                alignParentRight()
            }

            button("Send email") {
                id = 4
                onClick {
                    email("dummy2051@mailinator.com", "dummy", "dummy")
                }
            }.lparams {
                below(3)
                alignParentLeft()
                alignParentRight()
            }

            button("Open gallery") {
                id = 5
                onClick {
                    startActivity<GalleryActivity>()
                }
            }.lparams {
                below(4)
                alignParentLeft()
                alignParentRight()
            }
        }
    }
}
