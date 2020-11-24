package org.olebas.chat.api.component

import org.olebas.chat.api.constant.ErrorResponse
import org.olebas.chat.api.exception.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {

    @ExceptionHandler(ConversationIdInvalidException::class)
    fun conversationIdInvalid(conversationIdInvalidException: ConversationIdInvalidException):
            ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

}