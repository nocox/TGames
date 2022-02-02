package com.nocox.tgames.updown.domain.repository

import com.nocox.tgames.updown.domain.BaseTrump

interface BaseTrumpRepository {
    fun saveBaseTrump(baseTrump: BaseTrump)
    fun findCurrentBaseTrump(): BaseTrump
}