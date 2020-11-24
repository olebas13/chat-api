package org.olebas.chat.api.repository

import org.olebas.chat.api.model.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {

    fun findByConversationId(conversationId: Long): List<Message>

}