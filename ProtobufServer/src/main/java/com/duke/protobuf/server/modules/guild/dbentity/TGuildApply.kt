package com.duke.protobuf.server.modules.guild.dbentity

import com.duke.protobuf.data.ProtoMessages.CHARACTER_CLASS
import com.duke.protobuf.data.ProtoMessages.NGuildApplyInfo.APPLY_RESULT
import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GUILD_APPLY")
data class TGuildApply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(nullable = false)
    var characterId: Int = 0,
    @Column(nullable = false)
    var name: String = "",
    @Column(name = "CLASS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var clazz: CHARACTER_CLASS = CHARACTER_CLASS.NONE,
    @Column(nullable = false)
    var level: Int = 0,
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var result: APPLY_RESULT = APPLY_RESULT.NONE,
    @Column(nullable = false)
    var applyTime: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "GUILD_ID", referencedColumnName = "ID")
    var owner: TGuild? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TGuildApply

        return id != null && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }
}
