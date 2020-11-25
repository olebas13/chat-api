package org.olebas.chat.api.helper.`object`

data class MessageVO(
        val id: Long,
        val senderId: Long?,
        val recipientId: Long?,
        val conversationId: Long?,
        val body: String?,
        val createdAt: String
)
