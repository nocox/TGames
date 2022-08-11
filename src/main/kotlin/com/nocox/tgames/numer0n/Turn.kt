package com.nocox.tgames.numer0n

// 継承とsealedどっちを使うべきなのか？
interface Turn {
    val playerId: PlayerId
}

class CallTurn(
    override val playerId: PlayerId,
    val settingNumber: SettingNumber,
    val callNumber: CallNumber,
    val callAnswer: CallAnswer
    ) : Turn

class SkillTurn(
    override val playerId: PlayerId,
    val skill: Skill
) : Turn

class PlayerId

class Skill