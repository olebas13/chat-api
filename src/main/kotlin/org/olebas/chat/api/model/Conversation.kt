package org.olebas.chat.api.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
class Conversation(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @ManyToOne(optional = true)
        @JoinColumn(name = "sender_id", referencedColumnName = "id")
        var sender: User? = null,

        @ManyToOne(optional = true)
        @JoinColumn(name = "recipient_id", referencedColumnName = "id")
        var recipient: User? = null,

        @DateTimeFormat
        val createdAt: Date = Date.from(Instant.now())
) {

    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    private var messages: Collection<Message>? = null

}
