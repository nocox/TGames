package com.nocox.tgames.updown.usecase

import com.nocox.tgames.updown.domain.*
import com.nocox.tgames.updown.domain.repository.DrawHistoryRepository
import com.nocox.tgames.updown.domain.repository.TargetTrumpRepository

class DrawTargetTrumpUseCase(
    val targetTrumpRepository: TargetTrumpRepository,
    val drawHistoryRepository: DrawHistoryRepository
) {
    fun invoke(): TargetTrump {
        val deck = Deck.getCurrentDeck(drawHistoryRepository.findDrawHistory())
        val targetTrump = deck.drawTargetTrump()
        targetTrumpRepository.saveTargetTrump(targetTrump)
        drawHistoryRepository.saveDrawHistory(Draw(targetTrump.trump))
        return targetTrump
    }
}