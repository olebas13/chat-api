package org.olebas.chat.api.service

import org.olebas.chat.api.model.User

interface UserService {

    fun attemptRegistration(userDetails: User): User

    fun listUsers(currentUser: User): List<User>

    fun updateUserStatus(currentUser: User, updateDetails: User): User

    fun retrieveUserData(username: String): User?

    fun retrieveUserData(id: Long): User?

    fun usernameExists(username: String): Boolean

}