package com.gremlinprojects.projectB.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.ecs.components.TouchableComponent
import ktx.ashley.allOf
import ktx.ashley.get

class TouchHandlerSystem(
    private val camera: OrthographicCamera
) : IteratingSystem(
    allOf(TouchableComponent::class, RenderComponent::class).get()
) {
    private val touchPoint = Vector3(0f, 0f, 0f)
    private var propagating = false

    override fun update(deltaTime: Float) {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
            propagating = true
            for (entity in entities) {
                processEntity(entity, deltaTime)
                if (!propagating) break
            }
            propagating = false
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bounds = entity[RenderComponent.mapper]?.sprite?.boundingRectangle
        if (bounds != null) {
            if(bounds.contains(touchPoint.x, touchPoint.y))
                entity[TouchableComponent.mapper]?.onTouch?.let { propagating = it(entity) }
        }
    }
}