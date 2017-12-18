package messages

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import com.github.salomonbrys.kodein.with
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class Message(val text: String)

interface MessageSender {
    fun send(message: Message)
}

class MessageSenderStub : MessageSender, AnkoLogger {
    override fun send(message: Message) {
        info {
            "Sending message: ${message.text}"
        }
    }
}

val meassagingModule = Kodein.Module {

    constant("dummyMessage") with ( "Dummy hello message" )

    bind<MessageSender>() with singleton { MessageSenderStub() }

}