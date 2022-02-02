package com.nocox.tgames.updown.domain.repository

import com.nocox.tgames.updown.domain.UpDownCall

interface UpDownCallRepository {
    fun saveUpDownCall(call: UpDownCall)
    fun findUpDownCall(): UpDownCall
}