package org.olebas.chat.api.component

import org.olebas.chat.api.helper.`object`.UserListVO
import org.olebas.chat.api.helper.`object`.UserVO
import org.olebas.chat.api.model.User
import org.springframework.stereotype.Component

@Component
class UserAssembler {

    fun toUserVO(user: User): UserVO {
        return UserVO(user.id, user.username, user.phoneNumber,
                user.status, user.createdAt.toString())
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userVOList = users.map { toUserVO(it) }
        return UserListVO(userVOList)
    }

}