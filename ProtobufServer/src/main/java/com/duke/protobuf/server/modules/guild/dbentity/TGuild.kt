package com.duke.protobuf.server.modules.guild.dbentity

import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GUILD")
data class TGuild(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(nullable = false)
    var name: String = "",
    @Column(nullable = false)
    var leaderId: Int = 0,
    @Column(nullable = false)
    var leaderName: String = "",
    @Column(nullable = false, length = 255)
    var notice: String = "",
    @Column(nullable = false)
    var createTime: LocalDateTime = LocalDateTime.now(),
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TGuild

        return id != null && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }
}
