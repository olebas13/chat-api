package org.olebas.chat.api.exception

class MessageEmptyException(
        override val message: String = "A message cannot be empty"
) : RuntimeException()