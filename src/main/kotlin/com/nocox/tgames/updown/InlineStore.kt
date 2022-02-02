package com.nocox.tgames.updown

import com.nocox.tgames.updown.domain.BaseTrump
import com.nocox.tgames.updown.domain.Trump
import com.nocox.tgames.updown.domain.UpDownCall

var inlineStore = InlineStore(mutableListOf())

class InlineStore(var gameEvents: MutableList<GameEvent>)

class GameEvent(val baseCard: BaseTrump) {
    var callEvents: MutableList<CallEvent> = mutableListOf()
    var drawEvents: MutableList<DrawEvent> = mutableListOf()
    var turn: Int = 0
}

class CallEvent(val call: UpDownCall)
class DrawEvent(val trump: Trump)