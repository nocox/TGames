package com.nocox.tgames.updown.usecase

import com.nocox.tgames.updown.domain.BaseTrump
import com.nocox.tgames.updown.domain.repository.BaseTrumpRepository

class GetBaseTrumpUseCase(val baseTrumpRepository: BaseTrumpRepository) {
    fun invoke(): BaseTrump {
        return baseTrumpRepository.findCurrentBaseTrump()
    }
}