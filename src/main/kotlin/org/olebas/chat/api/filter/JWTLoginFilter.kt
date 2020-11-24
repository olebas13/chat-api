package org.olebas.chat.api.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.olebas.chat.api.security.AccountCredentials
import org.olebas.chat.api.service.TokenAuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class JWTLoginFilter(
        url: String,
        authManager: AuthenticationManager
) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {

    init {
        authenticationManager = authManager
    }

    @Throws(IOException::class, AuthenticationException::class, ServletException::class)
    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(req.inputStream, AccountCredentials::class.java)
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.username,
                        credentials.password,
                        emptyList()
                )
        )
    }

    override fun successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse,
                                          chain: FilterChain, auth: Authentication) {
        TokenAuthenticationService.addAuthentication(res, auth.name)
    }

}