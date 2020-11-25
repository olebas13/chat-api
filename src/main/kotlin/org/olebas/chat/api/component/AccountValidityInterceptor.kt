package org.olebas.chat.api.component

import org.olebas.chat.api.exception.UserDeactivatedException
import org.olebas.chat.api.model.User
import org.olebas.chat.api.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

@Component
class AccountValidityInterceptor(
        val userRepository: UserRepository
) : HandlerInterceptorAdapter() {

    @Throws(UserDeactivatedException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal: Principal? = request.userPrincipal

        if (principal != null) {
            val user = userRepository.findByUsername(principal.name) as User

            if (user.accountStatus == "deactivated") {
                throw UserDeactivatedException("The account of this user has been deactivated.")
            }
        }

        return super.preHandle(request, response, handler)
    }
}