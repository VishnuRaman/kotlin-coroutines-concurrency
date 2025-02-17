package com.vishnuraman.playground

import com.vishnuraman.aktors.ActorSystem
import com.vishnuraman.aktors.Behavior
import com.vishnuraman.aktors.Behaviors
import org.slf4j.LoggerFactory

object AktorPlayground {
    val logger = LoggerFactory.getLogger(this::class.java)

    val loggingBehavior: Behavior<String> = Behaviors.receiveMessage { message ->
        logger.info("Message received: $message")

        Behaviors.same()
    }

    suspend fun main() {
        ActorSystem.app(loggingBehavior, "FirstActorSystem") { guardian ->
            (1..100).forEach { i ->
                guardian `!` "Message $i"
            }
        }
    }
}

suspend fun main() {
    AktorPlayground.main()
}