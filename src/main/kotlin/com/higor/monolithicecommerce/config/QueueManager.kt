package com.higor.monolithicecommerce.config

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.ConnectionFactory


class QueueManager {

    companion object {
        fun publish(message: String) {
            val factory = ConnectionFactory()
            factory.newConnection().use { connection ->
                val channel = connection.createChannel()

                channel.exchangeDeclare("minhaExchange", BuiltinExchangeType.FANOUT)
                val queueName = channel.queueDeclare("", false, false, false, null).queue
                channel.basicPublish("minhaExchange", queueName, null, message.toByteArray(charset("UTF-8")))
            }
        }
    }
}