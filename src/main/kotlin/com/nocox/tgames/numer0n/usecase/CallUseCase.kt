package com.nocox.tgames.numer0n.usecase

import com.nocox.tgames.numer0n.CallNumber
import com.nocox.tgames.numer0n.domain.Call
import com.nocox.tgames.numer0n.domain.CallRepository

class CallUseCase(private val callRepository: CallRepository) {
    operator fun invoke(callNumber: CallNumber) {
        val call = Call(callNumber)
        callRepository.save(call)
    }
}