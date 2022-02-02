package com.nocox.tgames.updown.domain.repository

import com.nocox.tgames.updown.domain.UpDownCall

interface CallHistoryRepository {
    fun saveCallHistory(call: UpDownCall)
}