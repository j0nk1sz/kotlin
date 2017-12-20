package com.jonkisz.kotlinforandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.appKodein
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity(), AnkoLogger, KodeinInjected {
    override val injector = KodeinInjector()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(appKodein())

        relativeLayout {
            val messageText = editText {
                id = 1
                hint = "Message text here"
            }.lparams {
                alignParentLeft()
                alignParentRight()
                alignParentTop()
            }

            button("Send") {

                id = 2
                onClick {
                    startActivity<SecondActivity>("com.jonkisz.kotlinforandroid.MESSAGE" to messageText.text.toString())

                }

            }.lparams {
                below(1)
                alignParentLeft()
                alignParentRight()
            }
        }


    }
}
