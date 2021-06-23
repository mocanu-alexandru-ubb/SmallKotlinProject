package com.gremlinprojects.projectB.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import ktx.ashley.mapperFor

class TouchableComponent : Component {
    companion object {
        val mapper = mapperFor<TouchableComponent>()
    }

    var onTouch: (Entity) -> Boolean = { false }
}