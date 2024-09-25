package com.duke.protobuf.server.modules.character.service

import com.duke.protobuf.server.modules.character.repo.CharacterRepository
import com.duke.protobuf.server.modules.game.entity.PlayerCharacter
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class EquipService(
    private val characterRepo: CharacterRepository,
) {
    @Transactional
    fun equipItem(playerCharacter: PlayerCharacter, slot: Int, itemId: Int, putOn: Boolean): Boolean {
        // 如果背包中没有装备，直接失败
        if (!playerCharacter.itemManager.hasItem(itemId)) {
            return false
        }

        val tChar = characterRepo.getOne(playerCharacter.dbId)
        val equipBa = this.updateEquips(tChar.equips ?: byteArrayOf(), slot, itemId, putOn)
        tChar.equips = equipBa
        characterRepo.save(tChar)

        return true
    }

    private fun updateEquips(equips: ByteArray, slot: Int, itemId: Int, putOn: Boolean): ByteArray {
        var resultEquips = equips
        if (equips.size < 7 * 4) {
            resultEquips = ByteArray(7 * 4)
            for (i in equips.indices) {
                resultEquips[i] = equips[i]
            }
        }
        val destOffset = slot * 4
        if (putOn) {
            // 穿装备逻辑
            val resultBuf = intToBytes(itemId)
            resultEquips[destOffset] = resultBuf[0]
            resultEquips[destOffset + 1] = resultBuf[1]
            resultEquips[destOffset + 2] = resultBuf[2]
            resultEquips[destOffset + 3] = resultBuf[3]
        } else {
            // 脱装备逻辑
            resultEquips[destOffset] = 0
            resultEquips[destOffset + 1] = 0
            resultEquips[destOffset + 2] = 0
            resultEquips[destOffset + 3] = 0
        }
        return resultEquips
    }

    private fun bytesToInt(byteArray: ByteArray, offset: Int = 0): Int {
        require(byteArray.size >= offset + 4) { "ByteArray must have at least 4 bytes" }
        return (byteArray[0 + offset].toInt() and 0xFF) shl 24 or
                (byteArray[1 + offset].toInt() and 0xFF) shl 16 or
                (byteArray[2 + offset].toInt() and 0xFF) shl 8 or
                (byteArray[3 + offset].toInt() and 0xFF)
    }

    private fun intToBytes(value: Int): ByteArray {
        return byteArrayOf(
            value.toByte(),
            (value shr 8).toByte(),
            (value shr 16).toByte(),
            (value shr 24).toByte(),
        )
    }
}
