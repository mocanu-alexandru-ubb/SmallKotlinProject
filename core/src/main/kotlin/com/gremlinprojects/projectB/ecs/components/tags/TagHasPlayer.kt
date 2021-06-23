package com.gremlinprojects.projectB.ecs.components.tags

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class TagHasPlayer : Component {
    companion object {
        val mapper = mapperFor<TagHasPlayer>()
    }
}