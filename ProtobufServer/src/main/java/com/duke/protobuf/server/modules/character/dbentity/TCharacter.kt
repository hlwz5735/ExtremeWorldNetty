package com.duke.protobuf.server.modules.character.dbentity

import com.duke.protobuf.data.CHARACTER_CLASS
import com.duke.protobuf.server.modules.user.dbentity.TPlayer
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "GAME_CHARACTER")
data class TCharacter (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var tid: Int? = null,
    var name: String? = null,
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
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")
    var player: TPlayer? = null,
    @OneToMany(mappedBy = "owner")
    var items: List<TCharacterItem> = emptyList(),
    @OneToOne(mappedBy = "owner")
    var bag: TCharacterBag? = null,
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
