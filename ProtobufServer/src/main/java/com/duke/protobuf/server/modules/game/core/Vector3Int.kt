package com.duke.protobuf.server.modules.game.core

import com.duke.protobuf.data.NVector3
import kotlin.math.sqrt

data class Vector3Int(
    var x: Int = 0,
    var y: Int = 0,
    var z: Int = 0
) {

    val magnitude: Float
        get() = sqrt(sqrMagnitude.toDouble()).toFloat()

    val sqrMagnitude
        get() = x * x + y * y + z * z

    constructor(nVector3: NVector3) : this(nVector3.x, nVector3.y, nVector3.z)

    fun toNVector3(): NVector3 {
        return NVector3.newBuilder()
            .setX(this.x)
            .setY(this.y)
            .setZ(this.z)
            .build()
    }

    operator fun plus(other: Vector3Int): Vector3Int {
        return Vector3Int(this.x + other.x, this.y + other.y, this.z + other.z)
    }

    operator fun plusAssign(other: Vector3Int) {
        this.set(this.x + other.x, this.y + other.y, this.z + other.z)
    }

    operator fun minus(other: Vector3Int): Vector3Int {
        return Vector3Int(this.x - other.x, this.y - other.y, this.z - other.z)
    }

    operator fun minusAssign(other: Vector3Int) {
        this.set(this.x - other.x, this.y - other.y, this.z - other.z)
    }

    operator fun times(other: Vector3Int): Vector3Int {
        return Vector3Int(this.x * other.x, this.y * other.y, this.z * other.z)
    }

    operator fun times(scale: Int): Vector3Int {
        return Vector3Int(this.x * scale, this.y * scale, this.z * scale)
    }

    operator fun timesAssign(other: Vector3Int) {
        this.set(this.x * other.x, this.y * other.y, this.z * other.z)
    }

    operator fun timesAssign(scale: Int) {
        this.set(this.x * scale, this.y * scale, this.z * scale)
    }

    fun set(x: Int, y: Int, z: Int) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun clamp(min: Vector3Int, max: Vector3Int) {
        this.x = max.x.coerceAtMost(min.x.coerceAtLeast(this.x))
        this.y = max.y.coerceAtMost(min.y.coerceAtLeast(this.y))
        this.z = max.z.coerceAtMost(min.z.coerceAtLeast(this.z))
    }

    companion object {
        val Zero = Vector3Int(0, 0, 0)
        val One = Vector3Int(1, 1, 1)
        val Up = Vector3Int(0, 1, 0)
        val Down = Vector3Int(0, -1, 0)
        val Left = Vector3Int(-1, 0, 0)
        val Right = Vector3Int(1, 0, 0)

        fun distance(a: Vector3Int, b: Vector3Int): Float {
            return (a - b).magnitude
        }

        fun min(a: Vector3Int, b: Vector3Int): Vector3Int {
            return Vector3Int(
                a.x.coerceAtMost(b.x),
                a.y.coerceAtMost(b.y),
                a.z.coerceAtMost(b.z),
            )
        }

        fun max(a: Vector3Int, b: Vector3Int): Vector3Int {
            return Vector3Int(
                a.x.coerceAtLeast(b.x),
                a.y.coerceAtLeast(b.y),
                a.z.coerceAtLeast(b.z)
            )
        }
    }
}