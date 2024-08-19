package com.duke.protobuf.server.modules.user.dbentity

import com.duke.protobuf.server.modules.character.dbentity.TCharacter
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "GAME_PLAYER")
data class TPlayer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    var user: TUser? = null,
    @OneToMany(mappedBy = "player")
    var characters: MutableList<TCharacter> = ArrayList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TPlayer

        return id != null && id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
