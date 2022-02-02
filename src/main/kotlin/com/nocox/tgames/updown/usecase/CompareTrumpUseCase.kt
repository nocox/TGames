package com.nocox.tgames.updown.usecase

import com.nocox.tgames.PlayingCard
import com.nocox.tgames.updown.domain.repository.BaseTrumpRepository
import com.nocox.tgames.updown.domain.repository.TargetTrumpRepository

class CompareTrumpUseCase(
    val targetTrumpRepository: TargetTrumpRepository,
    val baseTrumpRepository: BaseTrumpRepository
) {
    fun invoke(): PlayingCard.CardCompareResult {
        val targetTrump = targetTrumpRepository.findCurrentTargetTrump()
        val baseTrump = baseTrumpRepository.findCurrentBaseTrump()

        return baseTrump.compareTargetTrump(targetTrump)
    }
}