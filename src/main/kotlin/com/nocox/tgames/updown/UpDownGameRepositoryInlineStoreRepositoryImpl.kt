package com.nocox.tgames.updown

import com.nocox.tgames.updown.domain.*
import com.nocox.tgames.updown.domain.repository.*

class UpDownGameRepositoryInlineStoreRepositoryImpl : BaseTrumpRepository, DrawHistoryRepository, UpDownCallRepository,
    CallHistoryRepository, TargetTrumpRepository {
    override fun saveBaseTrump(baseTrump: BaseTrump) {
        val gameEvent = GameEvent(baseTrump)
        inlineStore.gameEvents.add(gameEvent)
    }

    override fun findCurrentBaseTrump(): BaseTrump {
        val drawEvents = inlineStore.gameEvents.last().drawEvents
        val turn = inlineStore.gameEvents.last().turn
        return if (turn == 0) {
            inlineStore.gameEvents.last().baseCard
        } else {
            BaseTrump(drawEvents[turn - 1].trump)
        }
    }

    override fun findDrawHistory(): DrawHistory {
        val gameEvents = inlineStore.gameEvents
        val drawTrumps = if (gameEvents.size != 0) gameEvents.last().drawEvents else listOf()
        return DrawHistory(drawTrumps.map { Draw(it.trump) })
    }

    override fun saveDrawHistory(draw: Draw) {
        val drawEvent = DrawEvent(draw.trump)
        inlineStore.gameEvents.last().drawEvents.add(drawEvent)
    }

    override fun saveCallHistory(call: UpDownCall) {
        val callEvent = CallEvent(call)
        inlineStore.gameEvents.last().callEvents.add(callEvent)
    }

    override fun saveUpDownCall(call: UpDownCall) {}

    override fun findUpDownCall(): UpDownCall {
        return inlineStore.gameEvents.last().callEvents.last().call
    }

    override fun saveTargetTrump(targetTrump: TargetTrump) {}

    override fun findCurrentTargetTrump(): TargetTrump {
        val targetTrump = inlineStore.gameEvents.last().drawEvents.last().trump
        return TargetTrump(targetTrump)
    }

    fun nextTurn() {
        inlineStore.gameEvents.last().turn ++
    }
}