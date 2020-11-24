package org.olebas.chat.api.service

import org.olebas.chat.api.model.Message
import org.olebas.chat.api.model.User

interface MessageService {

    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message

}