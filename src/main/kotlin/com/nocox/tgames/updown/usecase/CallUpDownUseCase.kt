package com.nocox.tgames.updown.usecase

import com.nocox.tgames.updown.domain.repository.CallHistoryRepository
import com.nocox.tgames.updown.domain.UpDownCall
import com.nocox.tgames.updown.domain.repository.UpDownCallRepository
import java.lang.IllegalArgumentException

class CallUpDownUseCase(
    val upDownCallRepository: UpDownCallRepository,
    val callHistoryRepository: CallHistoryRepository
) {
    fun invoke(callStr: String) {
        val call = fromStr(callStr)
        upDownCallRepository.saveUpDownCall(call)
        callHistoryRepository.saveCallHistory(call)
    }

    companion object {
        fun fromStr(chooseStr: String): UpDownCall {
            return when (chooseStr) {
                "up", "Up", "UP" -> UpDownCall.UP
                "down", "Down", "DOWN" -> UpDownCall.DOWN
                else -> throw IllegalArgumentException()
            }
        }
    }
}