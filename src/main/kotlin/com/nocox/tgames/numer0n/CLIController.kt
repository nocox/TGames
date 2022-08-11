package com.nocox.tgames.numer0n

import com.nocox.tgames.numer0n.usecase.CallUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

fun main() {
    val cliController = CLIController()

    cliController.setNumber()

    while (true){
        val chooseStr = readLine() ?: throw IllegalArgumentException()
        cliController.call(chooseStr)
        cliController.getStatus()
    }
}

class CLIController : KoinComponent {
    private val callUseCase by inject<CallUseCase>()

    fun setNumber() {

    }

    fun call(call: String) {
        callUseCase(CallNumber(call))
    }

    fun getStatus() {

    }
}