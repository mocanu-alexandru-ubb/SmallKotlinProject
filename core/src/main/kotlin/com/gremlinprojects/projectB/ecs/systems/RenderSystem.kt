package com.gremlinprojects.projectB.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.graphics.use

class RenderSystem(
    private val batch: Batch,
    private val camera: OrthographicCamera
) : SortedIteratingSystem(
    allOf(RenderComponent::class).get(),
    compareBy { entity: Entity -> entity[RenderComponent.mapper]?.z }) {

    override fun update(deltaTime: Float) {
        forceSort()
        camera.update()
        batch.use(camera.combined) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val renderComponent = entity[RenderComponent.mapper]!!
        if (renderComponent.visible)
            renderComponent.sprite.draw(batch)
    }

}