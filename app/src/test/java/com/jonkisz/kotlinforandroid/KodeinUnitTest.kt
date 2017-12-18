package com.jonkisz.kotlinforandroid

import android.provider.ContactsContract
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class KodeinUnitTest {

    @Test
    fun one() {
        val kodein = ConfigurableKodein()
        kodein.addConfig {
            bind<String>() with provider { "one" }
        }

        val injectedString: String = kodein.instance()
        assertEquals("one", injectedString)
    }


    @Test
    fun two() {
        val kodein = ConfigurableKodein()
        kodein.addConfig {
            bind<String>("two") with provider { "two" }
        }

        val injectedString: String = kodein.instance("two")
        assertEquals("two", injectedString)
    }

    class Database
    class MessageRepository(val db: Database)

    @Test
    fun three() {
        val kodein = ConfigurableKodein()
        kodein.addConfig {
            bind<Database>() with singleton { Database() }
            bind<MessageRepository>() with provider { MessageRepository(instance()) }
        }

        val injectedRepository: MessageRepository = kodein.instance()
        assertNotNull(injectedRepository.db)

    }
}
