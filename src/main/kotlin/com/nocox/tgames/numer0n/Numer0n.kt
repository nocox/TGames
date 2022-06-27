package com.nocox.tgames.numer0n

fun main() {
    val setting = SettingNumber("516")

    showCallAnswer(Numer0n(setting).call(CallNumber("145"))) // 0eat,2bite
    showCallAnswer(Numer0n(setting).call(CallNumber("123"))) // 0eat,1bite
    showCallAnswer(Numer0n(setting).call(CallNumber("424"))) // 0eat,0bite
    showCallAnswer(Numer0n(setting).call(CallNumber("390"))) // 0eat,0bite
    showCallAnswer(Numer0n(setting).call(CallNumber("165"))) // 0eat,3bite
    showCallAnswer(Numer0n(setting).call(CallNumber("156"))) // 1eat,2bite
    showCallAnswer(Numer0n(setting).call(CallNumber("516"))) // 3eat,0bite
}

private fun showCallAnswer(callAnswer: CallAnswer) {
    println(callAnswer.eat.value.toString() + "eat," + callAnswer.bite.value.toString() + "bite")
}

class Numer0n(private val settingNumber: SettingNumber) {
    fun call(callNumber: CallNumber): CallAnswer {
        val eat = callNumber.eat(settingNumber)
        val bite = callNumber.bite(settingNumber)
        return CallAnswer(eat, bite)
    }
}

interface NNumber {
    val value: String

    fun eat(nNumber: NNumber): Eat{
        if (value.length != nNumber.value.length) {
            throw IllegalArgumentException()
        }

        val eatCount = value.filterIndexed { index, c ->
            c == nNumber.value[index]
        }.count()
        return Eat(eatCount)
    }

    fun bite(nNumber: NNumber): Bite{
        if (value.length != nNumber.value.length) {
            throw IllegalArgumentException()
        }

        val biteCount = value.filterIndexed { index, c ->
            nNumber.value.contains(c)
        }.count()
        return Bite(biteCount - eat(nNumber).value)
    }
}

class CallNumber(override val value: String): NNumber

class SettingNumber(override val value: String): NNumber

class Eat(val value: Int)

class Bite(val value: Int)

class CallAnswer(val eat: Eat, val bite: Bite)