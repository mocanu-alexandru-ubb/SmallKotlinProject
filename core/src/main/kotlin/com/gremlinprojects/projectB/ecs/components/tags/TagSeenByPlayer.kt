package com.gremlinprojects.projectB.ecs.components.tags

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class TagSeenByPlayer : Component {
    companion object {
        val mapper = mapperFor<TagSeenByPlayer>()
    }
}