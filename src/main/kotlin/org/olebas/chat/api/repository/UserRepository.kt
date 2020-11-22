package org.olebas.chat.api.repository

import org.olebas.chat.api.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun findByPhoneNumber(phoneNumber: String): User?

}