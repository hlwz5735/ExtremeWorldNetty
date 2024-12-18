package com.duke.protobuf.server.modules.character.dbentity

import org.hibernate.Hibernate
import javax.persistence.*

/**
 * 角色-道具记录表实体
 */
@Entity
@Table(name = "CHARACTER_ITEM")
data class TCharacterItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(nullable = false)
    var itemId: Int = 0,
    @Column(nullable = false)
    var itemCount: Int = 0,
    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID")
    var owner: TCharacter? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TCharacterItem

        return id != null && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}
