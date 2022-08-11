package com.nocox.tgames.numer0n.impl

import com.nocox.tgames.numer0n.domain.Call

var store = InMemoryStore()

class InMemoryStore(){
    var callEvent: MutableList<CallEvent> = mutableListOf()
}

class CallEvent(val call: Call)