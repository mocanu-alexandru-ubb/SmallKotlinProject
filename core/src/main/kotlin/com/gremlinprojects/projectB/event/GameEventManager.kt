package com.gremlinprojects.projectB.event

import ktx.collections.GdxSet
import java.util.*

class GameEventManager {
    private val listener = EnumMap<GameEventType, GdxSet<GameEventListener>>(GameEventType::class.java)

    fun addListener(type: GameEventType, gameEventListener: GameEventListener) {
        var eventListenerSet = listener[type]
        if (eventListenerSet == null) {
            eventListenerSet = GdxSet()
            listener[type] = eventListenerSet
        }
        eventListenerSet.add(gameEventListener)
    }

    fun dispatchEvent(type: GameEventType, data: GameEvent? = null) {
        listener[type]?.forEach { it.onEvent(type, data) }
    }
}