package com.nocox.tgames.numer0n.domain

import com.nocox.tgames.numer0n.CallNumber

class Call(val callNumber: CallNumber)

interface CallRepository{
    fun resolve(callNumber: CallNumber)
    fun save(call: Call)
}