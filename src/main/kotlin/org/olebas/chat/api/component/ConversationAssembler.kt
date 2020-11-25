package org.olebas.chat.api.component

import org.olebas.chat.api.helper.`object`.ConversationListVO
import org.olebas.chat.api.helper.`object`.ConversationVO
import org.olebas.chat.api.helper.`object`.MessageVO
import org.olebas.chat.api.model.Conversation
import org.olebas.chat.api.service.ConversationService
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(
        val conversationService: ConversationService,
        val messageAssembler: MessageAssembler
) {

    fun toConversationVO(conversation: Conversation, userId: Long): ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = ArrayList()
        conversation.messages.mapTo(conversationMessages) {
            messageAssembler.toMessageVO(it)
        }

        return ConversationVO(conversation.id, conversationService.nameSecondParty(conversation, userId),
                conversationMessages)
    }

    fun toConversationVOList(conversations: ArrayList<Conversation>, userId: Long): ConversationListVO {
        val conversationVOList = conversations.map {
            toConversationVO(it, userId)
        }
        return ConversationListVO(conversationVOList)
    }
}