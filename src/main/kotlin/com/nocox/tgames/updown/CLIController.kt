package com.nocox.tgames.updown

import com.nocox.tgames.updown.usecase.*

fun main() {
    val cliController = CLIController()

    cliController.drawBaseTrump()

    while (true) {
        println("> current base : " + cliController.getBaseTrump())

        println("> Please up or down")
        when (cliController.call()) {
            CLIController.CallResult.SUCCESS -> {
            }
            CLIController.CallResult.FAILURE -> {
                println("!!!!!! No choice !!!!!!")
                continue
            }
        }

        println("draw: " + cliController.drawTargetTrump())
        println("compare: " + cliController.compareBaseAndTargetTrump())
        println("result: " + cliController.getGameResult())
        cliController.drawBaseTrump()
        cliController.nextTurn()
    }
}

class CLIController() {
    private val repositoryImpl = UpDownGameRepositoryInlineStoreRepositoryImpl()

    private val drawBaseTrumpUseCase = DrawBaseTrumpUseCase(repositoryImpl, repositoryImpl)
    private val getBaseTrumpUseCase = GetBaseTrumpUseCase(repositoryImpl)
    private val callUpDownUseCase = CallUpDownUseCase(repositoryImpl, repositoryImpl)
    private val drawTargetTrumpUseCase = DrawTargetTrumpUseCase(repositoryImpl, repositoryImpl)
    private val compareTrumpUseCase = CompareTrumpUseCase(repositoryImpl, repositoryImpl)
    private val getGameResultUseCase = GetGameResultUseCase(repositoryImpl, repositoryImpl, repositoryImpl)

    fun drawBaseTrump() {
        drawBaseTrumpUseCase.invoke()
    }

    fun getBaseTrump(): String {
        val baseTrump = getBaseTrumpUseCase.invoke()
        return baseTrump.trump.card.outStr()
    }

    fun call(): CallResult {
        return try {
            val chooseStr = readLine() ?: throw IllegalArgumentException()
            callUpDownUseCase.invoke(chooseStr)
            CallResult.SUCCESS
        } catch (e: IllegalArgumentException) {
            CallResult.FAILURE
        }
    }

    enum class CallResult {
        SUCCESS, FAILURE
    }

    fun drawTargetTrump(): String {
        val targetTrump = drawTargetTrumpUseCase.invoke()
        return targetTrump.trump.card.outStr()
    }

    fun compareBaseAndTargetTrump(): String {
        val compareResult = compareTrumpUseCase.invoke()
        return compareResult.name
    }

    fun getGameResult(): String {
        val gameResult = getGameResultUseCase.invoke()
        return gameResult.name
    }

    fun nextTurn() {
        repositoryImpl.nextTurn()
    }

}