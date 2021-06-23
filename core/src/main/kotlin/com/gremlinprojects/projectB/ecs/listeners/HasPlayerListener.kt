package com.gremlinprojects.projectB.ecs.listeners

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.core.PooledEngine
import com.gremlinprojects.projectB.ecs.components.MoveToComponent
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.ecs.components.tags.TagHasPlayer
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.plusAssign

class HasPlayerListener(
    private val player: Entity,
    private val engine: PooledEngine
) : EntityListener {
    override fun entityAdded(entity: Entity) {
        engine.getEntitiesFor(allOf(TagHasPlayer::class).get())[0].remove(TagHasPlayer::class.java)
        player += entity[RenderComponent.mapper]?.sprite?.let { MoveToComponent(it.boundingRectangle) }!!
    }

    override fun entityRemoved(entity: Entity) {

    }
}