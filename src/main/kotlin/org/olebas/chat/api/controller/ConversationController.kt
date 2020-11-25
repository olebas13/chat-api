package org.olebas.chat.api.controller

import org.olebas.chat.api.component.ConversationAssembler
import org.olebas.chat.api.helper.`object`.ConversationListVO
import org.olebas.chat.api.helper.`object`.ConversationVO
import org.olebas.chat.api.model.User
import org.olebas.chat.api.repository.UserRepository
import org.olebas.chat.api.service.ConversationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/conversations")
class ConversationController(
        val conversationService: ConversationService,
        val conversationAssembler: ConversationAssembler,
        val userRepository: UserRepository
) {

    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversations = conversationService.listUserConversations(user.id)

        return ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.id))
    }

    @GetMapping
    @RequestMapping("/{conversation_id}")
    fun show(@PathVariable(name = "conversation_id") conversationId: Long,
             request: HttpServletRequest): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversationThread = conversationService.retrieveThread(conversationId)

        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.id))
    }
}