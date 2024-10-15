package com.duke.protobuf.server.modules.character.dbentity

import com.duke.protobuf.data.ProtoMessages.NQuestInfo.QUEST_STATUS
import javax.persistence.*

@Entity
@Table(name = "CHARACTER_QUEST")
data class TCharacterQuest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var questId: Int? = null,
    var target1: Int? = null,
    var target2: Int? = null,
    var target3: Int? = null,
    @Enumerated(EnumType.ORDINAL)
    var status: QUEST_STATUS? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARACTER_ID")
    var owner: TCharacter? = null,
)
