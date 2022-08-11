package com.nocox.tgames.numer0n.impl

import com.nocox.tgames.numer0n.CallNumber
import com.nocox.tgames.numer0n.domain.Call
import com.nocox.tgames.numer0n.domain.CallRepository

class InMemoryCallRepository : CallRepository{
    override fun resolve(callNumber: CallNumber) {
        TODO("Not yet implemented")
    }

    override fun save(call: Call) {
        store.callEvent.add(CallEvent(call))
    }
}