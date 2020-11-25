package org.olebas.chat.api.service

import org.olebas.chat.api.model.Conversation
import org.olebas.chat.api.model.User


interface ConversationService {

    fun createConversation(userA: User, userB: User): Conversation

    fun conversationExists(userA: User, userB: User): Boolean

    fun getConversation(userA: User, userB: User): Conversation?

    fun retrieveThread(conversationId: Long): Conversation

    fun listUserConversations(userId: Long): ArrayList<Conversation>

    fun nameSecondParty(conversation: Conversation, userId: Long): String

}