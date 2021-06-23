package com.gremlinprojects.projectB.ecs.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.gremlinprojects.projectB.ecs.components.MoveToComponent
import com.gremlinprojects.projectB.ecs.components.NodeComponent
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.ecs.components.tags.TagHasPlayer
import com.gremlinprojects.projectB.ecs.components.tags.TagPlayer
import com.gremlinprojects.projectB.ecs.components.tags.TagRequestPlayer
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.has

class RequestPlayerSystem : IteratingSystem(
    allOf(TagRequestPlayer::class, NodeComponent::class, RenderComponent::class).get()
) {
    private lateinit var player: Entity
    private var nodeWithPlayer: NodeComponent? = null
    private var solved = false

    override fun addedToEngine(engine: Engine) {
        player = engine.getEntitiesFor(allOf(TagPlayer::class).get())[0]
        super.addedToEngine(engine)
    }

    override fun update(deltaTime: Float) {
        if (player.has(MoveToComponent.mapper))
            entities.forEach { it.remove(TagRequestPlayer::class.java) }
        else {
            nodeWithPlayer = engine.getEntitiesFor(allOf(TagHasPlayer::class).get())[0][NodeComponent.mapper]
            for (entity in entities) {
                processEntity(entity, deltaTime)
                if (solved) break
            }
            solved = false
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        if (entity[NodeComponent.mapper]!!.id in nodeWithPlayer!!.neighbors) {
            player.add(entity[RenderComponent.mapper]?.sprite?.boundingRectangle?.let {
                MoveToComponent(it)
            })
            entity.add(TagHasPlayer())
            solved = true
        }
        entity.remove(TagRequestPlayer::class.java)
    }
}