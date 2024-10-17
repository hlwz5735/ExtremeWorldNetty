package com.duke.protobuf.server.modules.guild.dbentity

import com.duke.protobuf.data.ProtoMessages.CHARACTER_CLASS
import com.duke.protobuf.data.ProtoMessages.NGuildMemberInfo.GUILD_TITLE
import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GUILD_MEMBER")
data class TGuildMember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var characterId: Int = 0,
    var name: String = "",
    @Column(name = "CLASS")
    @Enumerated(EnumType.ORDINAL)
    var clazz: CHARACTER_CLASS = CHARACTER_CLASS.NONE,
    var level: Int = 0,
    @Enumerated(EnumType.ORDINAL)
    var title: GUILD_TITLE = GUILD_TITLE.NONE,
    var joinTime: LocalDateTime = LocalDateTime.now(),
    var lastTime: LocalDateTime = LocalDateTime.of(1996, 5, 1, 12, 0),
    @ManyToOne
    @JoinColumn(name = "GUILD_ID", referencedColumnName = "ID")
    var owner: TGuild? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TGuildMember

        return id != null && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }
}
