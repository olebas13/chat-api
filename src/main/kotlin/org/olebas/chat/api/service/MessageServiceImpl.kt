package org.olebas.chat.api.service

import org.olebas.chat.api.exception.MessageEmptyException
import org.olebas.chat.api.exception.MessageRecipientInvalidException
import org.olebas.chat.api.model.Conversation
import org.olebas.chat.api.model.Message
import org.olebas.chat.api.model.User
import org.olebas.chat.api.repository.ConversationRepository
import org.olebas.chat.api.repository.MessageRepository
import org.olebas.chat.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
        val repository: MessageRepository,
        val conversationRepository: ConversationRepository,
        val conversationService: ConversationService,
        val userRepository: UserRepository
) : MessageService {

    @Throws(MessageEmptyException::class, MessageRecipientInvalidException::class)
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)

        if (optional.isPresent) {
            val recipient = optional.get()

            if (messageText.isNotEmpty()) {
                val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                    conversationService.getConversation(sender, recipient) as Conversation
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message = Message(sender = sender,
                        recipient = recipient,
                        body = messageText,
                        conversation = conversation)
                repository.save(message)
                return message
            }
        } else {
            throw MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
        }
        throw MessageEmptyException()
    }

}