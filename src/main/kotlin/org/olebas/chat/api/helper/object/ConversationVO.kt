package org.olebas.chat.api.helper.`object`

data class ConversationVO(
        val conversationId: Long,
        val secondPartyUsername: String,
        val messages: ArrayList<MessageVO>
)
