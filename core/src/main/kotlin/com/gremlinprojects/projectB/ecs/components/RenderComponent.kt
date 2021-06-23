package com.gremlinprojects.projectB.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class RenderComponent : Component, Pool.Poolable {
    companion object {
        val mapper = mapperFor<RenderComponent>()
    }

    val sprite = Sprite()
    var z = 0
    var visible = true

    override fun reset() {
        sprite.apply {
            texture = null
            setPosition(0f, 0f)
            setColor(0f, 0f, 0f, 1f)
        }
    }
}