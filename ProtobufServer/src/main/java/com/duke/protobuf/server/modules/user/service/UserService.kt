package com.duke.protobuf.server.modules.user.service

import com.duke.protobuf.netty.NettySession
import com.duke.protobuf.netty.SessionUtil
import com.duke.protobuf.server.modules.game.net.OnlineUser
import com.duke.protobuf.server.modules.map.service.MapService
import com.duke.protobuf.server.modules.user.OnlineCharacterManager
import com.duke.protobuf.server.modules.user.dbentity.TPlayer
import com.duke.protobuf.server.modules.user.dbentity.TUser
import com.duke.protobuf.server.modules.user.repo.PlayerRepository
import com.duke.protobuf.server.modules.user.repo.UserRepository
import com.duke.protobuf.structure.DTuple
import io.netty.channel.Channel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserService(
    private val repo: UserRepository,
    private val playerRepo: PlayerRepository,
    private val mapService: MapService,
    private val onlineCharacterManager: OnlineCharacterManager,
) {
    /**
     * 处理用户登录逻辑
     *
     * 登录成功后会创建用户会话
     */
    @Transactional
    fun userLogin(username: String, password: String, channel: Channel): DTuple<TUser?, String?> {
        val user = repo.getByUsername(username) ?: return DTuple(null, "用户不存在。")
        if (user.password != password) {
            return DTuple(null, "密码不匹配。")
        }
        if (user.player == null) {
            return DTuple(null, "用户不存在对应玩家信息。")
        }

        logger.debug("用户包含角色{}个", user.player!!.characters.size)
        // 校验通过后，将用户信息放入会话
        val onlineUser = OnlineUser(user)
        val session = NettySession(channel, onlineUser)
        onlineUser.session = session
        SessionUtil.addSessionToChannel(session)
        return DTuple(user, null)
    }

    /**
     * 处理用户注册
     */
    @Transactional
    fun registerUser(username: String, password: String): DTuple<Boolean, String?> {
        val user = repo.getByUsername(username)
        if (user != null) {
            return DTuple(false, "用户名已存在。")
        }

        val newUser = TUser(
            username = username,
            password = password,
            registerTime = LocalDateTime.now(),
        )
        val newPlayer = TPlayer(user = newUser)

        repo.save(newUser)
        playerRepo.save(newPlayer)

        return DTuple(true, null)
    }

    /**
     * 用户离开游戏
     */
    fun userLeave(session: NettySession<OnlineUser>) {
        val character = session.user.character ?: return
        val mapId = character.mapId ?: return
        // 通知朋友下线
        character.friendManager.tellFriendOffline()

        logger.info("位于地图 {} 的角色 {}-{} 离开游戏。", mapId, character.id, character.name)

        mapService.characterLeave(mapId, character)
        onlineCharacterManager.removeByCharacterId(character.dbId)
        if (character.team != null) {
            character.team!!.memberLeave(character)
        }
        session.user.character = null
    }

    companion object { private val logger = LoggerFactory.getLogger(UserService::class.java) }
}
