package com.duke.protobuf.server.modules.user.dbentity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GAME_USER")
data class TUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var registerTime: LocalDateTime? = null,
    @OneToOne(mappedBy = "user")
    var player: TPlayer? = null
)

@Entity
@Table(name = "GAME_PLAYER")
data class TPlayer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: TUser? = null,
    @OneToMany(mappedBy = "player")
    var characters: List<TCharacter> = emptyList()
)

@Entity
@Table(name = "GAME_CHARACTER")
data class TCharacter (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var tid: Int? = null,
    var name: String? = null,
    @Column(name = "class")
    var clazz: Int? = null,
    var mapId: Int = 1,
    var mapPosX: Int? = null,
    var mapPosY: Int? = null,
    var mapPosZ: Int? = null,
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    var player: TPlayer? = null
)