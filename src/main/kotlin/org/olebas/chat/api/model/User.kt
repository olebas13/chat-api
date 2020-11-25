package org.olebas.chat.api.model

import org.olebas.chat.api.listener.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "`user`")
@EntityListeners(UserListener::class)
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,

        @Column(unique = true)
        @Size(min = 2)
        var username: String = "",

        @Size(min = 8, max = 15)
        @Column(unique = true)
        @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
        var phoneNumber: String = "",

        @Size(min = 6, max = 60)
        var password: String = "",

        var status: String = "available",

        @Pattern(regexp = "\\A(activated|deactivated)\\z")
        var accountStatus: String = "activated",

        @DateTimeFormat
        var createdAt: Date = Date.from(Instant.now())
) {
        @OneToMany(mappedBy = "sender", targetEntity = Message::class)
        private var sendMessages: Collection<Message>? = null

        @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
        private var receivedMessages: Collection<Message>? = null
}