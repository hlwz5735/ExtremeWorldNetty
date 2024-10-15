package com.duke.protobuf.server.modules.character.dbentity

import org.hibernate.Hibernate
import javax.persistence.*

/**
 * 角色背包实体记录
 */
@Entity
@Table(name = "CHARACTER_BAG")
data class TCharacterBag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    /** 已解锁的格子数 */
    @Column(nullable = false)
    var unlockedCellCount: Int = 0,

    @Lob
    @Column(name = "ITEMS", columnDefinition = "BLOB", nullable = true)
    var items: ByteArray? = null,

    @OneToOne
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID")
    var owner: TCharacter? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TCharacterBag

        return id != null && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}
