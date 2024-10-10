package com.duke.protobuf.server.modules.character.dbentity

import com.duke.protobuf.data.CHARACTER_CLASS
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "CHARACTER_FRIEND")
data class TCharacterFriend(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var friendId: Int? = null,
    var friendName: String = "",
    var clazz: CHARACTER_CLASS = CHARACTER_CLASS.UNRECOGNIZED,
    var level: Int = 0,
    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID")
    var owner: TCharacter? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as TCharacterFriend

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
