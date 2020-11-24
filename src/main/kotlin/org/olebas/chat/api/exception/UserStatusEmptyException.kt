package org.olebas.chat.api.exception

class UserStatusEmptyException(
        override val message: String = "A user's status cannot be empty"
) : RuntimeException()