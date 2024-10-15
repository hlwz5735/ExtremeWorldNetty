package com.duke.protobuf.server.modules.character.dbentity

import com.duke.protobuf.data.ProtoMessages.CHARACTER_CLASS
import com.duke.protobuf.server.modules.user.dbentity.TPlayer
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "GAME_CHARACTER")
data class TCharacter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    /** 配置表中的ID，其他地方都是configId */
    var tid: Int? = null,
    var level: Int = 1,
    var name: String? = null,
    /** 经验值 */
    @Column(name = "EXP_VAL")
    var exp: Long = 0,
    @Column(name = "CLASS")
    @Enumerated(EnumType.ORDINAL)
    var clazz: CHARACTER_CLASS = CHARACTER_CLASS.NONE,
    var mapId: Int = 1,
    @Column(name = "MAP_POS_X")
    var mapPosX: Int? = null,
    @Column(name = "MAP_POS_Y")
    var mapPosY: Int? = null,
    @Column(name = "MAP_POS_Z")
    var mapPosZ: Int? = null,
    @Column(name = "CARRIED_MONEY", nullable = false)
    var carriedMoney: Long = 0,
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")
    var player: TPlayer? = null,
    @OneToMany(mappedBy = "owner")
    var items: List<TCharacterItem> = emptyList(),
    @OneToOne(mappedBy = "owner")
    var bag: TCharacterBag? = null,
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "EQUIPS", columnDefinition = "BLOB", nullable = true)
    var equips: ByteArray? = byteArrayOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TCharacter

        return id != null && id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
