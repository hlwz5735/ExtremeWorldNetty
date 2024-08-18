package com.duke.protobuf.server.modules.user.dbentity

import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GAME_USER")
data class TUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var registerTime: LocalDateTime? = null,
    @OneToOne(mappedBy = "user")
    var player: TPlayer? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TUser

        return id != null && id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
