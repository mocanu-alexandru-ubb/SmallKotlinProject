package com.gremlinprojects.projectB.event

interface GameEventListener {
    fun onEvent(eventType: GameEventType, data: GameEvent? = null)
}