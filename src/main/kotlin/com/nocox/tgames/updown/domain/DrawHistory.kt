package com.nocox.tgames.updown.domain

class DrawHistory(val draws: List<Draw>) {
    fun contains(trump: Trump): Boolean {
        return draws.map { it.trump }.contains(trump)
    }
}

data class Draw(val trump: Trump)