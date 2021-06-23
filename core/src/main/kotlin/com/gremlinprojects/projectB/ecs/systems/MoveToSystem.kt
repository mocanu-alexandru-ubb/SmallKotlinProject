package com.gremlinprojects.projectB.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.gremlinprojects.projectB.ecs.components.MoveToComponent
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.utils.config.MOVE_SPEED
import ktx.ashley.allOf
import ktx.ashley.get
import kotlin.math.atan2

class MoveToSystem : IteratingSystem(
    allOf(MoveToComponent::class, RenderComponent::class).get()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val target = entity[MoveToComponent.mapper]!!
        entity[RenderComponent.mapper]?.sprite?.let {
            if (target.cosX == null) {
                val degrees = (atan2(
                    (target.targetPosition.y - it.y).toDouble(),
                    (target.targetPosition.x - it.x).toDouble()
                ) * 180.0 / Math.PI).toFloat()
                target.cosX = MathUtils.cosDeg(degrees)
                target.sinY = MathUtils.sinDeg(degrees)
            }

            val dst2 = Vector2(target.targetPosition.x, target.targetPosition.y).dst2(it.x, it.y)
            val moveThisFrame = MOVE_SPEED * deltaTime
            if (dst2 > moveThisFrame * moveThisFrame) {
                it.setPosition(
                    it.x + moveThisFrame * target.cosX!!,
                    it.y + moveThisFrame * target.sinY!!
                )
            }
            else {
                it.setPosition(
                    target.targetPosition.x,
                    target.targetPosition.y
                )
                entity.remove(MoveToComponent::class.java)
            }
        }
    }
}