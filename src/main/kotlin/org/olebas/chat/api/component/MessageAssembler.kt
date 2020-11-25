package org.olebas.chat.api.component

import org.olebas.chat.api.helper.`object`.MessageVO
import org.olebas.chat.api.model.Message
import org.springframework.stereotype.Component

@Component
class MessageAssembler {

    fun toMessageVO(message: Message): MessageVO {
        return MessageVO(message.id, message.sender?.id, message.recipient?.id,
                message.conversation?.id, message.body, message.createdAt.toString())
    }

}