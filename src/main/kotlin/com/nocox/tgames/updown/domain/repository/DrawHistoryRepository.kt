package com.nocox.tgames.updown.domain.repository

import com.nocox.tgames.updown.domain.Draw
import com.nocox.tgames.updown.domain.DrawHistory

interface DrawHistoryRepository {
    fun findDrawHistory(): DrawHistory
    fun saveDrawHistory(draw: Draw)
}