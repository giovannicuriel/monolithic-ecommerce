package com.higor.monolithicecommerce.config

import com.rabbitmq.client.ConnectionFactory


class QueueManager {

    companion object {
        fun publish(message: String, queueName: String) {
            val factory = ConnectionFactory()
            factory.newConnection().use { connection ->
                val channel = connection.createChannel()
                channel.queueDeclare(queueName, false, false, false, null)
                channel.basicPublish("", queueName, null, message.toByteArray(charset("UTF-8")))
            }
        }
    }
}