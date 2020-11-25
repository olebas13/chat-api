package org.olebas.chat.api.controller

import org.olebas.chat.api.component.MessageAssembler
import org.olebas.chat.api.helper.`object`.MessageVO
import org.olebas.chat.api.model.User
import org.olebas.chat.api.repository.UserRepository
import org.olebas.chat.api.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/messages")
class MessageController(
        val messageService: MessageService,
        val userRepository: UserRepository,
        val messageAssembler: MessageAssembler
) {

    fun create(@RequestBody messageDetails: MessageRequest, request: HttpServletRequest): ResponseEntity<MessageVO> {
        val principal = request.userPrincipal
        val sender = userRepository.findByUsername(principal.name) as User
        val message = messageService.sendMessage(sender, messageDetails.recipientId, messageDetails.message)
        return ResponseEntity.ok(messageAssembler.toMessageVO(message))
    }

    data class MessageRequest(val recipientId: Long, val message: String)

}