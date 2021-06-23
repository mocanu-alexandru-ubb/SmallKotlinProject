package com.gremlinprojects.projectB.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Rectangle
import ktx.ashley.mapperFor

class MoveToComponent(
    val targetPosition: Rectangle,
    val nodeId: Int = 0
) : Component {
    companion object {
        val mapper = mapperFor<MoveToComponent>()
    }

    var cosX: Float? = null
    var sinY: Float? = null
}