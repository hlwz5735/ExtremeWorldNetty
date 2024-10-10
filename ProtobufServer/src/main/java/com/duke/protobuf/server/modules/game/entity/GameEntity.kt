package com.duke.protobuf.server.modules.game.entity

import com.duke.protobuf.data.NEntity
import com.duke.protobuf.server.modules.game.core.Vector3Int

open class GameEntity(
    var id: Int = 0,
    var position: Vector3Int,
    var direction: Vector3Int,
    var speed: Int = 0
) {
    constructor(netEntity: NEntity) : this(
        id = netEntity.id,
        position = Vector3Int(netEntity.position),
        direction = Vector3Int(netEntity.direction),
        speed = netEntity.speed
    )

    val entityId get() = id

    fun toNetEntity(): NEntity {
        return NEntity.newBuilder()
            .setId(this.id)
            .setPosition(this.position.toNVector3())
            .setSpeed(this.speed)
            .setDirection(this.direction.toNVector3())
            .build()
    }
}
