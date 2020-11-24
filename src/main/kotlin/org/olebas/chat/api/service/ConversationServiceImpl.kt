package org.olebas.chat.api.service

import org.olebas.chat.api.exception.ConversationIdInvalidException
import org.olebas.chat.api.model.Conversation
import org.olebas.chat.api.model.User
import org.olebas.chat.api.repository.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationServiceImpl(
        val repository: ConversationRepository
) : ConversationService {

    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation(sender = userA, recipient = userB)
        repository.save(conversation)
        return conversation
    }

    override fun conversationExists(userA: User, userB: User): Boolean {
        return repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null
    }

    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null ->
                repository.findBySenderIdAndRecipientId(userA.id, userB.id)
            repository.findBySenderIdAndRecipientId(userB.id, userA.id) != null ->
                repository.findBySenderIdAndRecipientId(userB.id, userA.id)
            else -> null
        }
    }

    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)

        if (conversation.isPresent) {
            return conversation.get()
        }

        throw ConversationIdInvalidException("Invalid conversation id '$conversationId'")
    }

    override fun listUserConversations(userId: Long): List<Conversation> {
        val conversationList: ArrayList<Conversation> = ArrayList()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecipientId(userId))

        return conversationList
    }

    override fun nameSecondParty(conversation: Conversation, userId: Long): String {
        return if (conversation.sender?.id == userId) {
            conversation.recipient?.username as String
        } else {
            conversation.sender?.username as String
        }
    }
}