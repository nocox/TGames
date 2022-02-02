package com.nocox.tgames.updown.domain.repository

import com.nocox.tgames.updown.domain.TargetTrump

interface TargetTrumpRepository {
    fun saveTargetTrump(targetTrump: TargetTrump)
    fun findCurrentTargetTrump(): TargetTrump
}