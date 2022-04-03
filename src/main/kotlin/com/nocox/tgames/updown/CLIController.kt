package com.nocox.tgames.updown

import com.nocox.tgames.updown.domain.repository.BaseTrumpRepository
import com.nocox.tgames.updown.domain.repository.CallHistoryRepository
import com.nocox.tgames.updown.domain.repository.DrawHistoryRepository
import com.nocox.tgames.updown.domain.repository.TargetTrumpRepository
import com.nocox.tgames.updown.domain.repository.UpDownCallRepository
import com.nocox.tgames.updown.usecase.CallUpDownUseCase
import com.nocox.tgames.updown.usecase.CompareTrumpUseCase
import com.nocox.tgames.updown.usecase.DrawBaseTrumpUseCase
import com.nocox.tgames.updown.usecase.DrawTargetTrumpUseCase
import com.nocox.tgames.updown.usecase.GetBaseTrumpUseCase
import com.nocox.tgames.updown.usecase.GetGameResultUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main() {

    val implModule = module {
        single { UpDownGameRepositoryInlineStoreRepositoryImpl() }

        single<BaseTrumpRepository> { UpDownGameRepositoryInlineStoreRepositoryImpl() }
        single<CallHistoryRepository> { UpDownGameRepositoryInlineStoreRepositoryImpl() }
        single<DrawHistoryRepository> { UpDownGameRepositoryInlineStoreRepositoryImpl() }
        single<TargetTrumpRepository> { UpDownGameRepositoryInlineStoreRepositoryImpl() }
        single<UpDownCallRepository> { UpDownGameRepositoryInlineStoreRepositoryImpl() }

        single { GetBaseTrumpUseCase(get()) }
        single { DrawBaseTrumpUseCase(get(), get()) }
        single { CallUpDownUseCase(get(), get()) }
        single { DrawTargetTrumpUseCase(get(), get()) }
        single { CompareTrumpUseCase(get(), get()) }
        single { GetGameResultUseCase(get(), get(), get()) }
    }

    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(implModule)
        startCliUpDownGame()
    }
}

fun startCliUpDownGame() {
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

class CLIController : KoinComponent {
    private val repositoryImpl by inject<UpDownGameRepositoryInlineStoreRepositoryImpl>()
    private val drawBaseTrumpUseCase by inject<DrawBaseTrumpUseCase>()
    private val getBaseTrumpUseCase by inject<GetBaseTrumpUseCase>()
    private val callUpDownUseCase by inject<CallUpDownUseCase>()
    private val drawTargetTrumpUseCase by inject<DrawTargetTrumpUseCase>()
    private val compareTrumpUseCase by inject<CompareTrumpUseCase>()
    private val getGameResultUseCase by inject<GetGameResultUseCase>()

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