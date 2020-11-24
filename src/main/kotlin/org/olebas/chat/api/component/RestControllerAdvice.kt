package org.olebas.chat.api.component

import org.olebas.chat.api.constant.ErrorResponse
import org.olebas.chat.api.constant.ResponseConstants
import org.olebas.chat.api.exception.UserDeactivatedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeactivated(userDeactivatedException: UserDeactivatedException):
            ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value,
                userDeactivatedException.message)
        return ResponseEntity(res, HttpStatus.UNAUTHORIZED)
    }

}