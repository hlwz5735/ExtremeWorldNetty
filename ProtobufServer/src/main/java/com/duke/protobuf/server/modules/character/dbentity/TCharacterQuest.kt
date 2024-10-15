package com.duke.protobuf.server.modules.character.dbentity

import com.duke.protobuf.data.ProtoMessages.NQuestInfo.QUEST_STATUS
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "CHARACTER_QUEST")
data class TCharacterQuest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(nullable = false)
    var questId: Int = 0,

    var target1: Int? = null,
    var target2: Int? = null,
    var target3: Int? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var status: QUEST_STATUS = QUEST_STATUS.IN_PROGRESS,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARACTER_ID")
    var owner: TCharacter? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TCharacterQuest

        return id != null && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}
