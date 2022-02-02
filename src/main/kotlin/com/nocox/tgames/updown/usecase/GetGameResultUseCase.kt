package com.nocox.tgames.updown.usecase

import com.nocox.tgames.PlayingCard
import com.nocox.tgames.updown.domain.repository.BaseTrumpRepository
import com.nocox.tgames.updown.domain.repository.TargetTrumpRepository
import com.nocox.tgames.updown.domain.UpDownCall
import com.nocox.tgames.updown.domain.repository.UpDownCallRepository

class GetGameResultUseCase(
    val targetTrumpRepository: TargetTrumpRepository,
    val baseTrumpRepository: BaseTrumpRepository,
    val upDownCallRepository: UpDownCallRepository
) {
    fun invoke(): GameResult {
        val targetTrump = targetTrumpRepository.findCurrentTargetTrump()
        val baseTrump = baseTrumpRepository.findCurrentBaseTrump()
        val upDownCall = upDownCallRepository.findUpDownCall()
        val compareResult = baseTrump.compareTargetTrump(targetTrump)

        return judge(compareResult, upDownCall)
    }

    private fun judge(cardUpDown: PlayingCard.CardCompareResult, call: UpDownCall): GameResult {
        if (call == UpDownCall.UP && cardUpDown == PlayingCard.CardCompareResult.UP) {
            return GameResult.SUCCESS
        } else if(call == UpDownCall.DOWN && cardUpDown == PlayingCard.CardCompareResult.DOWN) {
            return GameResult.SUCCESS
        }
        return GameResult.FAILURE
    }

    enum class GameResult {
        SUCCESS, FAILURE
    }
}